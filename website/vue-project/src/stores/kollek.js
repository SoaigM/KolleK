import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useKollekStore = defineStore('kollek', () => {
    const categories = ref([])
    const minerals = ref([])
    function setCategories(list) {
        categories.value = list
      }
      function setMinerals(list) {
        minerals.value = list
      }
    return { categories, minerals, setCategories, setMinerals }
})