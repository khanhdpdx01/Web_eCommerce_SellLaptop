<template>
  <div class="body">
    <div class="container grid wide">
      <template v-for="(productLine, index) in this.getProductSplited">
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
      :total-pages="productPage.totalPages"
      :per-page="productPage.totalItems"
      :current-page="productPage.currentPage"
      @pagechanged="onPageChange"
    />
  </div>
</template>

<script>
import { mapActions, mapGetters, mapState } from "vuex";
import CardItem from "../components/card/CardItem.vue";
import Pagination from "../components/pagination/Pagination.vue";
export default {
  components: { CardItem, Pagination },
  computed: {
    ...mapState("product", ["searchText", "productPage"]),
    ...mapGetters("product", ["getProductSplited"])
  },
  async created() {
    await this.getProductAction({
      size: 20,
    });
  },
  methods: {
    ...mapActions("product", ["getAllProduct"]),

    async getProductAction(productPage) {
      await this.getAllProduct(productPage);
    },
    onPageChange(page) {
      this.productPage.currentPage = page;
      const productPage = {
        page: this.productPage.currentPage,
        size: 20,
        sort: "",
        keyword: this.searchText,
      };
      this.getProductAction(productPage);
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