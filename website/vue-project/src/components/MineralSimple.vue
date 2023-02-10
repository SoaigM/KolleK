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
    country: {
      type: String,
      required: true,
    },
    image: {
      type: String,
      required: true,
    },
  },
  data() {
    return { select: false }
  }
};
</script>

<template>
  <div class="mineral" @click="select=true" :class="{ selected: select }">
    <h1>{{ name }}</h1>
    <h3>{{ country }}</h3>
    <h3>{{ image }}</h3>
  </div>
</template>

<style>
.mineral:hover {
  background: #2dd290;
}

.selected {
  background: #2dd290;
}

.mineral {
  background: #f0f2f5;
  padding-bottom: 24px;
  padding-top: 10px;
  text-align: center;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgb(0 0 0 / 10%), 0 8px 16px rgb(0 0 0 / 10%);
  box-sizing: border-box;
  margin: 8px 0 0;
  padding: 20px 0 28px;
}

.mineral h1 {
  font-weight: 500;
  font-size: 24px;
}

.mineral h3 {
  font-size: 10px;
}
</style>