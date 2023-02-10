import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

import './assets/main.css'

const app = createApp(App)

app.config.globalProperties.$apiKolleK = 'http://127.0.0.1:5000'

app.use(createPinia())
app.use(router)

app.mount('#app')
