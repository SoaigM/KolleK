<script>
export default {
  props: {
    id: {
      type: Number,
      required: true,
    },
    name: {
      type: String,
      required: true,
    },
    color: {
      type: String,
      required: true,
    },
  },
};
</script>

<template>
  <div class="category">
    <h1>{{ name }}</h1>
    <h3>{{ color }}</h3>
  </div>
</template>

<style scoped>
.category:hover {
  background: #2dd290;
}

.category {
  background: #f0f2f5;
  padding-bottom: 24px;
  padding-top: 10px;
  text-align: center;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgb(0 0 0 / 10%), 0 8px 16px rgb(0 0 0 / 10%);
  box-sizing: border-box;
  margin: 8px 0 0;
  padding: 10px 0 12px;
}

.category h1 {
  font-weight: 500;
  font-size: 22px;
}

.category h3 {
  font-size: 10px;
}
</style>