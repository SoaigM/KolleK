<template>
    <div class="login">
        <h1 class="green">Login</h1>
        <h3>
            Log in your Kollek database
        </h3>
        <input v-model="username" placeholder="username">
        <input v-model="password" placeholder="password" type="password">
        <input type="submit" value="log in">
    </div>
</template>

<script>
export default {
  name: "App",
  data() {
    return {
      username: "",
      password: ""
    };
  },
  methods: {
    async login() {
      const { username, password } = this;
      const res = await fetch(
        "https://SomberHandsomePhysics--five-nine.repl.co/login",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify({ username, password })
        }
      );
      const data = await res.json();
      console.log(data);
    }
  }
};
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
    display: inline-block;
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
