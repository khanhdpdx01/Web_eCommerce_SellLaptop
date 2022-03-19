<template>
  <div class="cart-area grid wide">
    <div class="cart-details">
      <cart-header class="header" />
      <cart-item
        v-for="cartItem in this.cart.cartItems"
        :key="cartItem.laptopId"
        :cartItem="cartItem"
      />
    </div>
    <cart-user-info class="user-info" />
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import CartHeader from "./CartHeader.vue";
import CartItem from "./CartItem.vue";
import CartUserInfo from "./CartUserInfo.vue";
export default {
  components: { CartItem, CartHeader, CartUserInfo },
  async created() {
    await this.getCart();
    console.log('Done...')
  },
  computed: {
    ...mapState("cart", ["cart"]),
  },
  methods: {
    ...mapActions("cart", ["getCart"]),
  },
};
</script>

<style scoped>
@import "../../assets/css/grid.css";

.cart-area {
  display: flex;
  flex-direction: row;
}

.header {
  top: 20px;
  z-index: 99;
  position: sticky;
  margin-bottom: 12px;
}

.header::before {
  content: "";
  background: rgb(245, 245, 250);
  width: 100%;
  height: 20px;
  position: absolute;
  left: 0px;
  top: -20px;
  right: 0px;
}

.header::after {
  content: "";
  background: rgb(245, 245, 250);
  width: 100%;
  height: 10px;
  position: absolute;
  left: 0px;
  bottom: -10px;
  right: 0px;
}

.cart-details {
  width: 75%;
  margin-right: 1rem;
}

.user-info {
  width: 25%;
  position: sticky;
  top: 20px;
}
</style>