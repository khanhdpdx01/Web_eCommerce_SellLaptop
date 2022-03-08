<template>
  <div class="body">
    <div class="container grid wide">
      <template v-for="(productLine, index) in productLines">
        <div class="row sm-gutter" :key="index">
          <template v-for="productItem in productLine">
            <card-item
              class="col l-2-4 m-4 c-6"
              :productItem="productItem"
              :key="productItem.laptopId"
            />
          </template>
        </div>
      </template>
    </div>
    <pagination
      :total-pages="getProductPage.totalPages"
      :per-page="getProductPage.totalItems"
      :current-page="getProductPage.currentPage"
      @pagechanged="onPageChange"
    />
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import CardItem from "../components/card/CardItem.vue";
import Pagination from "../components/pagination/Pagination.vue";
export default {
  components: { CardItem, Pagination },
  data() {
    return {
      productLines: [],
    };
  },
  computed: {
    ...mapGetters("product", ["getProductPage"]),
  },
  async created() {
    await this.getProductAction({
      size: 20,
    });
    this.productLines = this.splitArray(this.getProductPage.products, 5);
  },
  methods: {
    ...mapActions("product", ["getAllProduct"]),

    async getProductAction(productPage) {
      await this.getAllProduct(productPage);
    },
    onPageChange(page) {
      this.getProductPage.currentPage = page;
      const productPage = {
        page: this.getProductPage.currentPage,
        size: 20,
        sort: "",
        keyword: "",
      };
      this.getProductAction(productPage);
      this.productLines = this.splitArray(this.getProductPage.products, 5);
    },
    splitArray(array, part) {
      let tmp = [];
      for (let i = 0; i < array.length; i += part) {
        tmp.push(array.slice(i, i + part));
      } 
      return tmp;
    },
  },
};
</script>

<style scoped>
@import "../assets/css/grid.css";

.container {
  margin-bottom: 2rem;
}
</style>