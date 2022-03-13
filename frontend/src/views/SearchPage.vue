<template>
  <div class="body">
    <CardList :products="getProductSplited" />
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
import CardList from "../components/card/CardList.vue";
import Pagination from "../components/pagination/Pagination.vue";
export default {
  components: { Pagination, CardList },
  computed: {
    ...mapState("product", ["searchText", "productPage"]),
    ...mapGetters("product", ["getProductSplited"]),
  },
  async created() {
    await this.getAllProduct({
      keyword: this.searchText || "",
      size: 20,
    });
  },
  watch: {
    async "$route.query.q"() {
      await this.getAllProduct({
        keyword: this.searchText || "",
        size: 20,
      });
    },
  },
  methods: {
    ...mapActions("product", ["getAllProduct"]),

    onPageChange(page) {
      this.productPage.currentPage = page;
      const productPage = {
        page: this.productPage.currentPage,
        size: 20,
        sort: "",
        keyword: this.searchText,
      };
      this.getAllProduct(productPage);
    },
  },
};
</script>

<style></style>
