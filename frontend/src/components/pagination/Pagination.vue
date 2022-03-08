<template>
    <ul class="pagination">
      <li class="pagination-item">
        <a
          class="pagination__link"
          href=""
          :disabled="isInFirstPage"
          aria-label="Go to first page"
          @click.prevent="onClickFirstPage"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="1.5"
            stroke-linecap="round"
            stroke-linejoin="round"
            class="feather feather-chevrons-left w-4 h-4"
          >
            <polyline points="11 17 6 12 11 7" />
            <polyline points="18 17 13 12 18 7" />
          </svg>
        </a>
      </li>

      <li class="pagination-item">
        <a
          class="pagination__link"
          href=""
          :disabled="isInFirstPage"
          aria-label="Go to previous page"
          @click.prevent="onClickPreviousPage"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="1.5"
            stroke-linecap="round"
            stroke-linejoin="round"
            class="feather feather-chevron-left w-4 h-4"
          >
            <polyline points="15 18 9 12 15 6" />
          </svg>
        </a>
      </li>

      <li v-for="(page, index) in pages" :key="index" class="pagination-item">
        <a
          class="pagination__link"
          href=""
          :disabled="page.isDisabled"
          :class="{ 'pagination__link--active': isPageActive(page.name) }"
          :aria-label="`Go to page number ${page.name}`"
          @click.prevent="onClickPage(page.name)"
          >{{ page.name }}</a
        >
      </li>

      <li class="pagination-item">
        <a
          class="pagination__link"
          href=""
          :disabled="isInLastPage"
          aria-label="Go to next page"
          @click.prevent="onClickNextPage"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="1.5"
            stroke-linecap="round"
            stroke-linejoin="round"
            class="feather feather-chevron-right w-4 h-4"
          >
            <polyline points="9 18 15 12 9 6" />
          </svg>
        </a>
      </li>

      <li class="pagination-item">
        <a
          class="pagination__link"
          href=""
          :disabled="isInLastPage"
          aria-label="Go to last page"
          @click.prevent="onClickLastPage"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="1.5"
            stroke-linecap="round"
            stroke-linejoin="round"
            class="feather feather-chevrons-right w-4 h-4"
          >
            <polyline points="13 17 18 12 13 7" />
            <polyline points="6 17 11 12 6 7" />
          </svg>
        </a>
      </li>
    </ul>
</template>

<script>
export default {
  name: "Pagination",
  props: {
    maxVisibleButtons: {
      type: Number,
      required: false,
      default: 5,
    },
    totalPages: {
      type: Number,
      required: true,
      default: 1,
    },
    total: {
      type: Number,
      required: false,
    },
    perPage: {
      type: Number,
      required: true,
      default: 10,
    },
    currentPage: {
      type: Number,
      required: true,
      default: 1,
    },
  },
  computed: {
    startPage() {
      if (this.currentPage === 1 || this.totalPage <= this.maxVisibleButtons) {
        return 1;
      }

      return this.currentPage - 1;
    },
    endPage() {
      return Math.min(
        this.startPage + this.maxVisibleButtons - 1,
        this.totalPages
      );
    },
    pages() {
      const range = [];

      for (let i = this.startPage; i <= this.endPage; i += 1) {
        range.push({
          name: i,
          isDisabled: i === this.currentPage,
        });
      }
      return range;
    },
    isInFirstPage() {
      return this.currentPage === 1;
    },
    isInLastPage() {
      return this.currentPage === this.totalPages;
    },
  },
  methods: {
    onClickFirstPage() {
      this.$emit("pagechanged", 1);
    },
    onClickPreviousPage() {
      if (this.currentPage > 1) {
        this.$emit("pagechanged", this.currentPage - 1);
      }
    },
    onClickPage(page) {
      this.$emit("pagechanged", page);
    },
    onClickNextPage() {
      if (this.currentPage < this.totalPages) {
        this.$emit("pagechanged", this.currentPage + 1);
      }
    },
    onClickLastPage() {
      this.$emit("pagechanged", this.totalPages);
    },
    isPageActive(page) {
      return this.currentPage === page;
    },
  },
};
</script>

<style scoped>
@import '../../assets/css/pagination.css';
</style>