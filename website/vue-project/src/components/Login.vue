<template>
  <div class="login">
    <h1 class="green">Login</h1>
    <div class="login-form">
      <input v-model="username" placeholder="username" />
      <input v-model="password" placeholder="password" type="password" />
      <button class="login-btn" @click="login">
        Login
      </button>
    </div>
    <div class="login-error" v-if="error">
      Could not connect you to server
    </div>
  </div>
</template>

<script>
import { apiPostLogin } from '../api/api'
export default {
  name: "App",
  data() {
    return {
      username: "",
      password: "",
      error: false
    };
  },
  methods: {
    async login() {
      const { username, password } = this
      const res = await apiPostLogin(username, password)
      if (res) {
        this.error = false;
      } else {
        this.error = true;
      }
    },
  },
}
</script>

<style scoped>
h1 {
  font-weight: 500;
  font-size: 2.6rem;
  top: -10px;
}

h3 {
  font-size: 1.2rem;
}

.login {
  display: flex;
  flex-direction: column;
  max-width: 500px;
  margin: auto;
  width: 100%;
}

.login-form {
  display: flex;
  flex-direction: column;
  width: 100%;
}

.login-form > input {
  vertical-align: top;
  white-space: normal;
  text-align: center;
  font-size: 14px;
  padding: 1em 1.2em;
  margin: 4px;
}

.login-btn {
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
}

.login-btn:hover {
  background: #3da57c;
}

.login h1,
.login h3 {
  text-align: center;
}
</style>
