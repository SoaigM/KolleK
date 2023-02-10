<template>
  <div class="editor">
    <h1>Category</h1>
    <div class="editor-form">
      <input v-model="this.category.name" placeholder="name" />
      <input v-model="this.category.color" placeholder="color" />
      <div class="editor-btns">
        <button class="editor-btn" @click="edit">Create</button>
        <button class="editor-btn" @click="edit">Update</button>
        <button class="editor-btn" @click="edit">Delete</button>
      </div>
    </div>
  </div>
</template>

<script>
import { apiPostLogin } from "../api/api";
export default {
  name: "App",
  props: {
    category: {
      type: Object,
      default: { id: null, name: "", color: "" },
    },
  },
  methods: {
    async edit() {},
  },
};
</script>

<style scoped>
.editor-btns {
    display: flex;
    width: 100%;
}
.editor {
  display: flex;
  flex-direction: column;
  max-width: 500px;
  margin: auto;
  width: 100%;
}

.editor-form {
  display: flex;
  flex-direction: column;
  width: 100%;
}

.editor-form > input {
  vertical-align: top;
  white-space: normal;
  text-align: center;
  font-size: 14px;
  padding: 1em 1.2em;
  margin: 4px;
}

.editor-btn {
  color: #fff;
  background: #2dd290;
  vertical-align: top;
  white-space: normal;
  text-align: center;
  font-size: 18px;
  font-weight: 500;
  padding: 0.8em 1.2em;
  margin: 16px 4px 4px 4px;
  border-radius: 4px;
  border: 0;
  align-content: space-around;
}

.editor-btn:hover {
  background: #3da57c;
}

.editor h1 {
  text-align: center;
}
</style>
