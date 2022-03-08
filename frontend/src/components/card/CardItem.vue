<template>
    <div class="card">
        <div class="card-item">
            <img :src="productItem.linkImage" alt="">
        </div>
        <div class="card-content">
            <span class="card-title">{{ productItem.name}}</span>
            <div class="card-rating">
                <svg v-for="(item, index) in productItem.rating" :key="index" stroke="currentColor" fill="currentColor" stroke-width="0" viewBox="0 0 24 24" size="14" color="#fdd836" height="14" width="14" xmlns="http://www.w3.org/2000/svg" style="color: rgb(253, 216, 54);">
                    <path d="M12 17.27L18.18 21l-1.64-7.03L22 9.24l-7.19-.61L12 2 9.19 8.63 2 9.24l5.46 4.73L5.82 21z">
                    </path>
                </svg>
            </div>
            <div class="card-price">
                <span class="price">{{ Intl.NumberFormat('vi-VN', undefined).format(productItem.unitPrice)}}đ</span>
                <span class="discount">-{{productItem.discount}}%</span>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'CartItem',
    props: ['productItem'],
    // data() {
    //     return {
    //         // productItem: {
    //         //     image: 'https://salt.tikicdn.com/cache/400x400/media/catalog/producttmp/5d/7b/75/6394ab50dd6ecb4ef54799a76021d452.jpg.webp',
    //         //     title: 'Apple MacBook Pro M1 2020 - 13 Inchs (8GB / 16GB - 256GB / 512GB) - Hàng Chính Hãng',
    //         //     price: '30.000.000',
    //         //     rating: 4,
    //         //     discount: 30
    //         // }
    //     }
    // }

}
</script>

<style>
.card {
    display: flex;
    flex-direction: column;
    background-color: #fff;
    cursor: pointer;
    padding: 10px;
}

.card:hover {
    transform: translateY(-1px);
    box-shadow: 0 1px 20px 0 rgba(0, 0, 0, .05);
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