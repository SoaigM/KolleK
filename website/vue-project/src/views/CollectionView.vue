<script>
import CategorySimple from '@/components/CategorySimple.vue'
import { useKollekStore } from '../stores/kollek'
import { apiGetCategories } from '../api/api'

export default {
  components: {
    CategorySimple
  },
  data() {
    return {
      categories: []
    }
  },
  async mounted() {
    const newCategories = await apiGetCategories()
    this.categories = newCategories
  }
}
</script>

<template>
  Hello World
  <div v-for="item in this.categories" :key="item.id">
    <CategorySimple :id="item.id" :name="item.name" :color="item.color" />
  </div>
</template>
