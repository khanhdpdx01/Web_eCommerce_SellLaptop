<template>
  <div class="cart-item">
    <div class="container">
      <div class="product__images">
        <div class="product__checkbox">
          <input type="checkbox" />
        </div>
        <div class="product__img">
          <img :src="cartItem.laptop.linkImage" alt="" />
        </div>
        <div class="product__content">
          <a href="#">{{ cartItem.laptop.name }}</a>
        </div>
      </div>
      <div class="product__price">
        <span class="product__real-price">
          {{
            Intl.NumberFormat("vi-VN", undefined).format(cartItem.realPrice)
          }}đ
        </span>
        <del class="product__discount-price">
          {{
            Intl.NumberFormat("vi-VN", undefined).format(
              cartItem.discountPrice
            )
          }}đ
        </del>
      </div>
      <div class="group-input">
        <button class="btn-decs" @click="decrease()">-</button>
        <input type="text" class="input" :value="cartItem.quantity" @change="update()" ref="input"/>
        <button class="btn-inc" @click="increase()">+</button>
      </div>
      <div class="product__final-price">
        <span>
          {{
            Intl.NumberFormat("vi-VN", undefined).format(cartItem.totalPrice)
          }}đ
        </span>
      </div>
      <div class="product__remove" @click="deleteItem()">
        <div class="icons8-trash"></div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex';
export default {
  props: ["cartItem"],
  methods: {
    ...mapActions("cart", ["addProduct", "updateProduct", "deleteProduct"]),
    async decrease() {
      await this.addProduct({
        laptopId: this.cartItem.laptop.laptopId,
        quantity: -1
      });
    },
    async increase() {
      await this.addProduct({
        laptopId: this.cartItem.laptop.laptopId,
        quantity: 1
      });
    },
    async update() {
      await this.updateProduct({
        laptopId: this.cartItem.laptop.laptopId,
        quantity: this.$refs.input.value
      });
    },
    async deleteItem() {
      await this.deleteProduct({
        laptopId: this.cartItem.laptop.laptopId
      });
    }
  }
};
</script>

<style scoped>
@import "../../assets/css/base.css";

:root {
  --content-width: 400px;
  --price-width: 200px;
  --group-input-witdh: 120px;
  --btn-remove-width: 50px;
}

.cart-item {
  background-color: #fff;
  padding: 0 0.5rem;
  margin-bottom: 1rem;
}

.cart-item .container {
  height: 100px;
  display: flex;
  align-items: center;
  justify-content: space-around;
}

.product__images {
  width: 400px;
  display: flex;
  justify-content: space-between;
  padding-right: 1rem;
}

.product__checkbox {
  line-height: 80px;
  margin-right: 0.5rem;
}

.product__checkbox input {
  width: 18px;
  height: 18px;
}

.product__img {
  width: 80px;
  height: 80px;
}

.product__img img{
  width: 100%;
  height: 100%;
}

.product__content {
  width: calc(100% - 100px);
  padding-left: 1rem;
}

.product__content a {
  text-decoration: none;
  display: -webkit-box;
  text-overflow: ellipsis;
  overflow: hidden;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  font-size: 13px;
  color: rgb(36, 36, 36);
  margin-bottom: 5px;
  line-height: 20px;
}

.product__price {
  width: 200px;
  padding: 0 1rem;
  display: flex;
  flex-direction: column;
}

.product__real-price {
  font-size: 13px;
  margin-right: 0.5rem;
}

.product__discount-price {
  font-size: 11px;
}

.product__final-price {
  width: calc(100% - 770px);
}

.product__final-price span {
  font-size: 15px;
  color: red;
}

.group-input {
  width: 120px;
  margin: 0.8rem;
}

.product__remove {
  width: 50px;
  text-align: right;
  cursor: pointer;
}
</style>