import axios from "axios";

const api = axios.create(
  {baseURL: "https://quiz-deploy-production.up.railway.app"}
);

export default api;