const API = 'http://127.0.0.1:5000'

const apiPostLogin = async (username, password) => {
    const res = await fetch(
    API + '/login',
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        mode: 'cors',
        credentials: 'include',
        body: JSON.stringify({ username, password }),
      }
    );
    if (res.status == 200) {
        console.log(res.headers)
        
        //sessionStorage['session'] = res.headers[]
        return true
    } else {
        return false
    }
}

const apiGetCategories = async () => {
    const res = await fetch(
    API + '/category',
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
        mode: 'cors',
        credentials: 'include',
      }
    );
    const value = await res.json()
    return value['result']
}

const apiGetMinerals = async (id) => {
  const res = await fetch(
  API + '/category/' + id,
    {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
      mode: 'cors',
      credentials: 'include',
    }
  );
  const value = await res.json()
  return value['result']
}

export { apiPostLogin, apiGetCategories, apiGetMinerals }