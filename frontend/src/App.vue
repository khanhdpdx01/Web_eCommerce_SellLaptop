<template>
  <div id="app">
    <navbar/>
    <router-view/>
  </div>
</template>

<script>
// import ProductDetail from './components/product/ProductDetail.vue'
// import Home from './views/Home.vue'
import Navbar from './views/Navbar.vue'

export default {
  components: {  Navbar },
}
</script>

<style> 
@font-face {
  font-family: "Iosevka Mono";
  src: url(assets/font/Iosevka-Mono.woff2) format("woff2");
  font-weight: normal;
  font-style: normal;
}

* {
  box-sizing: border-box;
  padding: 0;
  margin: 0;
  font-family: "Iosevka Mono";
}

body {
  background-color: #f5f5f5;
}

.card-line {
  display: flex;
}

</style>

