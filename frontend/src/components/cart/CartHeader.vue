<template>
  <div class="cart-header">
    <div class="container">
      <div class="product__checkbox">
        <input type="checkbox" />
        <span class="price">Đơn giá</span>
      </div>
      <span class="quantity">Số lượng</span>
      <span class="final__price">Thành tiền</span>
      <div class="product__remove">
        <div class="icons8-trash"></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
@import "../../assets/css/base.css";

.container {
  background: rgb(255, 255, 255);
  padding: 0.5rem 0.5rem;
  border-radius: 4px;
  color: rgb(36, 36, 36);
  font-weight: 400;
  font-size: 13px;
  display: flex;
  /* grid-template-columns: 398px 190px 130px 150px 22px; */
  justify-content: space-between;
  -webkit-box-align: center;
  align-items: center;
}

.product__checkbox input {
  width: 18px;
  height: 18px;
}

.product__checkbox {
  width: 400px;
  display: flex;
}

.price {
  width: 100%;
  text-align: center;
  display: inline-block;
}

.quantity {
  width: 200px;
  text-align: center;
  display: inline-block;
}

.final__price {
  width: 200px;
  text-align: center;
  display: inline-block;
}

.product__remove {
  width: 50px;
  margin-left: calc(100% - 770px);
  text-align: right;
}
</style>