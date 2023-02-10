<template>
  <div class="login">
    <h1 class="green">Login</h1>
    <h3>Log in your Kollek database</h3>
    <div class="login-form">
      <input v-model="username" placeholder="username" />
      <input v-model="password" placeholder="password" type="password" />
      <button class="btn btn-primary" @click="login">
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

.login h1,
.login h3 {
  text-align: center;
}

@media (min-width: 1024px) {
  .login h1,
  .login h3 {
    text-align: left;
  }
}
</style>
