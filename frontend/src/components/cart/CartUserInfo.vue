<template>
  <div class="cart-user-info">
    <div class="address-area">
      <div class="header">
        <span>Giao tới</span>
        <a href="#">Thay đổi</a>
      </div>
      <div class="user-info">
        <span class="fullname">Nguyễn Tấn Khanh</span>
        <span class="phone-number">0966257231</span>
      </div>
      <p class="ship-address">
        8/10 Đồng Kè, Phường Hòa Khánh Bắc, Quận Liên Chiểu, Đà Nẵng
      </p>
    </div>
    <div class="price__total">
      <span class="price__text">Tổng cộng</span>
      <span class="price__content">5.000.000.000đ</span>
    </div>
    <button class="btn-purchase">Mua hàng</button>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>

.cart-user-info {
    display: flex;
    flex-direction: column;
    height: 100%;
}

.address-area {
    display: flex;
    flex-direction: column;
    background-color: #fff;
    margin-bottom: 1rem;
    padding: 1rem .5rem;
}

.header,
.price__total {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: .5rem;
}

.header span {
    color: rgb(128, 128, 137);
    font-size: 1rem;
}

.header a {
    text-decoration: none;
    font-size: 14px;
}

.user-info {
    display: flex;
    -webkit-box-align: center;
    align-items: center;
    color: rgb(56, 56, 61);
    margin-bottom: .5rem;
    font-weight: 600;
    font-size: 14px
}

.user-info .phone-number {
    padding-left: .5rem;
    margin-left: .5rem;
    border-left: 2px solid rgb(235, 235, 240);
}

.ship-address {
    color: rgb(128, 128, 137);
    font-weight: normal;
}

.price__total {
    width: 100%;
    background-color: #fff;
    margin-bottom: 1rem;
    padding: 1rem;
}

.price__total .price__text{
    font-weight: 300;
    color: rgb(51, 51, 51);
}

.price__total .price__content {
    color: rgb(254, 56, 52);
    font-size: 18px;
    font-weight: 400;
}

.btn-purchase {
    background: rgb(255, 66, 78);
    color: rgb(255, 255, 255);
    padding: 13px 10px;
    text-align: center;
    border-radius: 4px;
    border: none;
    width: 100%;
    display: block;
    cursor: pointer;
    font-size: 1rem;
}
</style>