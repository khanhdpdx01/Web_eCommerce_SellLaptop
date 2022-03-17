<template>
  <div class="product-detail grid wide">
    <div class="container">
      <div class="product-detail__img">
        <img :src="product.linkImage" alt="" />
      </div>
      <div class="product-detail__info">
        <div class="header">
          <p>
            {{ product.name }}
          </p>
          <div class="rating">
            <svg
              v-for="(item, index) in 5"
              :key="index"
              stroke="currentColor"
              fill="currentColor"
              stroke-width="0"
              viewBox="0 0 24 24"
              size="14"
              color="#fdd836"
              height="14"
              width="14"
              xmlns="http://www.w3.org/2000/svg"
              style="color: rgb(253, 216, 54)"
            >
              <path
                d="M12 17.27L18.18 21l-1.64-7.03L22 9.24l-7.19-.61L12 2 9.19 8.63 2 9.24l5.46 4.73L5.82 21z"
              ></path>
            </svg>
            <span class="rating-number">50 đánh giá</span>
          </div>
        </div>
        <div class="body">
          <div class="price">
            {{ Intl.NumberFormat("vi-VN", undefined).format(product.price) }}đ
          </div>
          <div class="add-to-cart">
            <span>Số lượng</span>
            <div class="group-input">
              <button class="btn-decs" @click="decrease()">-</button>
              <input type="text" class="input" :value="this.quantity" />
              <button class="btn-inc" @click="increase()">+</button>
            </div>
            <div class="group-button">
              <button class="btn btn-add" @click="addProductToCart()">
                Chọn mua
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="product-description">
      <div class="header">
        <p>Mô tả sản phẩm</p>
      </div>
      <div class="body">
        {{ product.description }}
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import ProductService from "../../services/ProductService";
import CartService from "../../services/CartService";
const service = new ProductService();
const cartService = new CartService();

export default {
  data() {
    return {
      product: "",
      quantity: 1,
    };
  },
  computed: {
    ...mapState("user", ["isAuthenticated"]),
    ...mapState("cart", ["cart"]),
  },
  async created() {
    const slug = this.$route.params.slug;
    const response = await service.getProductBySlug(slug);
    const data = await response.data;
    this.product = data;
  },
  methods: {
    ...mapActions("cart", ["getCart"]),
    increase() {
      this.quantity += 1;
    },
    decrease() {
      if (this.quantity > 1) {
        this.quantity -= 1;
      }
    },
    async addProductToCart() {
      if (!this.isAuthenticated) {
        this.$router.push({ name: "login" });
      } else {
        await cartService.addProduct({
          laptopId: this.product.laptopId,
          quantity: this.quantity,
        });
      }
    },
  },
};
</script>

<style scoped>
@import "../../assets/css/grid.css";
@import "../../assets/css/base.css";

.container {
  display: flex;
}

.product-detail__img {
  padding: 1rem;
  background-color: #fff;
}

.product-detail__img img {
  width: 444px;
  height: 444px;
}

.product-detail__info {
  margin-left: 1rem;
  padding: 0 1rem;
  background-color: #fff;
  width: 100%;
}

.header {
  height: 30%;
  padding: 1rem 1rem 1rem 0;
  border-bottom: 1px solid rgb(242, 242, 242);
}

.header p {
  font-size: 20px;
  padding-bottom: 1rem;
}

.header .rating-number {
  margin-left: 1rem;
  padding-left: 1rem;
  border-left: 1px solid rgba(0, 0, 0, 0.14);
}

.body {
  height: 70%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
.body .price {
  font-size: 30px;
  color: red;
  margin-top: 1rem;
}

.add-to-cart {
  padding: 1rem 1rem 1rem 0;
  border-top: 1px solid rgb(242, 242, 242);
}

.group-button {
  margin-top: 1rem;
}

.group-button .btn-add {
  color: rgb(255, 255, 255);
  background-color: rgb(255, 57, 69);
}

.product-description {
  width: 100%;
  height: auto;
  background-color: #fff;
  margin-top: 1rem;
  padding: 1rem;
}
</style>