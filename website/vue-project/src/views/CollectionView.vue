<script>
import CategorySimple from "@/components/CategorySimple.vue";
import MineralSimple from "@/components/MineralSimple.vue";
import { apiGetCategories, apiGetMinerals } from "../api/api";
import CategoryEditor from "../components/CategoryEditor.vue";
import MineralEditor from "../components/MineralEditor.vue";

export default {
  components: {
    CategorySimple,
    MineralSimple,
    CategoryEditor,
    MineralEditor,
  },
  data() {
    return {
      categories: [],
      minerals: [],
      selectedCategory: { id: null, name: "", color: "" },
      selectedMineral: { id: null, name: "", country: "", image: "" },
    };
  },
  methods: {
    async selectCategory(item) {
      this.minerals = await apiGetMinerals(item.id);
      this.selectedCategory = item;
      this.selectedMineral = { id: null, name: "", country: "", image: "" };
    },
    async selectMineral(item) {
      this.selectedMineral = item;
    },
  },
  async mounted() {
    const newCategories = await apiGetCategories();
    this.categories = newCategories;
  },
};
</script>

<template>
  <div class="collection">
    <div>
      <CategoryEditor :category="this.selectedCategory" />
    </div>
    <div>
      <MineralEditor :mineral="this.selectedMineral" />
    </div>
  </div>
  <div class="collection">
    <div class="collection-categories">
      <div v-for="item in this.categories" :key="item.id">
        <CategorySimple
          :id="item.id"
          :name="item.name"
          :color="item.color"
          @click="this.selectCategory(item)"
        />
      </div>
    </div>
    <div class="collection-minerals">
      <div v-for="item in this.minerals" :key="item.id">
        <MineralSimple
          :id="item.id"
          :name="item.name"
          :image="item.image"
          :country="item.country"
          @click="this.selectMineral(item)"
        />
      </div>
    </div>
  </div>
</template>

<style scoped>
.collection {
  display: grid;
  align-items: start;
  grid-template-columns: repeat(2, 1fr);
  gap: 10px;
  grid-auto-rows: minmax(100px, auto);
}
</style>