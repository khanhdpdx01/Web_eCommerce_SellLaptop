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
// import CardItem from "../components/card/CardItem.vue";
import CardList from "../components/card/CardList.vue";
import Pagination from "../components/pagination/Pagination.vue";
export default {
  components: { Pagination, CardList },
  computed: {
    ...mapState("product", ["productPage"]),
    ...mapGetters("product", ["getProductSplited"]),
  },
  async created() {
    await this.getAllProduct({
      page: 1,
      size: 20,
    });
  },
  methods: {
    ...mapActions("product", ["getAllProduct"]),

    onPageChange(page) {
      this.productPage.currentPage = page;
      this.getAllProduct({
        page: this.productPage.currentPage,
        size: 20
      });
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