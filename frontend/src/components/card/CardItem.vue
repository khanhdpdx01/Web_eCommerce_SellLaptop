<template>
  <router-link :to="{ path: `/${productItem.slug}` }" tag="div" class="card">
    <div class="card-item">
      <img :src="productItem.linkImage" alt="" />
    </div>
    <div class="card-content">
      <span class="card-title">{{ productItem.name }}</span>
      <div class="card-rating">
        <svg
          v-for="(item, index) in productItem.rating"
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
      </div>
      <div class="card-price">
        <span class="price"
          >{{
            Intl.NumberFormat("vi-VN", undefined).format(productItem.price)
          }}đ</span
        >
        <span class="discount">-{{ productItem.discount }}%</span>
      </div>
    </div>
  </router-link>
</template>

<script>
export default {
  name: "CartItem",
  props: ["productItem"],
};
</script>

<style scoped>
.card {
  display: flex;
  flex-direction: column;
  background-color: #fff;
  cursor: pointer;
  padding: 10px;
}

.card:hover {
  transform: translateY(-1px);
  box-shadow: 0 1px 20px 0 rgba(0, 0, 0, 0.05);
}

.card-item {
  height: 200px;
}

img {
  width: 100%;
  height: 100%;
}

.card-content {
  width: 100%;
  margin-top: 1rem;
}

.card-title {
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
  overflow: hidden;
}

.card-price .price {
  color: rgb(255, 66, 78);
  font-size: 16px;
  line-height: 24px;
  font-weight: 500;
}

.card-price .discount {
  padding: 0px 2px;
  line-height: 14px;
  font-size: 12px;
  font-weight: 400;
  margin-left: 8px;
  border: 1px solid rgb(255, 66, 78);
  border-radius: 2px;
  background-color: rgb(255, 240, 241);
  color: rgb(255, 66, 78);
}
</style>