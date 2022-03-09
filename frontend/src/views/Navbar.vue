<template>
  <nav class="navbar">
    <div class="container grid wide">
      <div class="logo-menu">
        <img class="logo" :src="require('../assets/images/logo.png')" alt="" />
        <div class="search-area">
          <input
            v-model="searchText"
            class="input-search"
            type="text"
            name=""
            placeholder="Tìm sản phẩm bạn muốn"
          />
          <button class="btn-search" @click="searchProduct()">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              x="0px"
              y="0px"
              width="20"
              height="30"
              viewBox="0 0 30 30"
              style="fill: #ffffff"
            >
              <path
                d="M 13 3 C 7.4889971 3 3 7.4889971 3 13 C 3 18.511003 7.4889971 23 13 23 C 15.396508 23 17.597385 22.148986 19.322266 20.736328 L 25.292969 26.707031 A 1.0001 1.0001 0 1 0 26.707031 25.292969 L 20.736328 19.322266 C 22.148986 17.597385 23 15.396508 23 13 C 23 7.4889971 18.511003 3 13 3 z M 13 5 C 17.430123 5 21 8.5698774 21 13 C 21 17.430123 17.430123 21 13 21 C 8.5698774 21 5 17.430123 5 13 C 5 8.5698774 8.5698774 5 13 5 z"
              ></path>
            </svg>
            Tìm Kiếm
          </button>
        </div>
      </div>
      <div class="personal-area">
        <div class="personal-area__img">
          <img
            src="https://img.icons8.com/external-bearicons-glyph-bearicons/64/000000/external-User-essential-collection-bearicons-glyph-bearicons.png"
          />
          <span>Tấn Khanh</span>
        </div>
        <div class="personal-area__cart">
          <img
            src="https://img.icons8.com/external-icongeek26-outline-icongeek26/64/ffffff/external-cart-user-interface-icongeek26-outline-icongeek26.png"
          />
        </div>
      </div>
    </div>
  </nav>
</template>

<script>
import { mapActions, mapGetters, mapMutations } from "vuex";
export default {
  data() {
    return {
      searchText: "",
    };
  },
  computed: {
    ...mapGetters("product", ["getSearchText"]),
  },
  methods: {
    ...mapActions("product", ["getAllProduct"]),
    ...mapMutations("product", ["SET_SEARCH_TEXT"]),

    async searchProduct() {
      const productPage = {
        page: 1,
        size: 30,
        keyword: this.searchText,
      };
      this.SET_SEARCH_TEXT(this.searchText);

      await this.getAllProduct(productPage);
    },
  },
};
</script>

<style scoped>
@import "../assets/css/grid.css";

.navbar {
  width: 100%;
  height: 100px;
  background-color: rgb(26, 148, 255);
  margin-bottom: 2rem;
}

.container {
  height: 100%;
  display: flex;
  flex-direction: row;
}

.logo-menu {
  width: 100%;
  display: flex;
  align-items: center;
}

.logo {
  width: auto;
  height: 100%;
}

.search-area {
  display: flex;
  margin-left: 5rem;
  width: 100%;
  height: 40%;
}

.input-search {
  border: 0px;
  padding: 0px 12px;
  font-size: 13px;
  border-radius: 2px 0px 0px 2px;
  flex: 1 1 0%;
  outline: none;
}

.btn-search {
  width: 120px;
  background-color: rgb(13, 92, 182);
  display: flex;
  align-items: center;
  padding: 10px;
  color: #fff;
}

.btn-search svg {
  margin-right: 0.5rem;
}

.personal-area {
  width: 30%;
  display: flex;
  flex-direction: row;
  padding: 1rem;
  justify-content: space-around;
}

.personal-area__img {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.personal-area__img img {
  width: 40px;
  height: 40px;
}

.personal-area__img span {
  font-size: 14px;
}

.personal-area__cart {
  display: flex;
  align-items: center;
}

.personal-area__cart img {
  width: 40px;
  height: 40px;
}
</style>