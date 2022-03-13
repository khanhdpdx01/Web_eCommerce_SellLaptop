<template>
  <div class="container grid wide">
      <template v-for="(productLine, index) in this.products">
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
</template>

<script>
import CardItem from './CardItem.vue'
export default {
    components: { CardItem },
    props: ['products'],
}
</script>

<style>

</style>