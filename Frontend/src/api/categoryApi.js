import axios from "axios";

const BASE_URL = "http://localhost:8080/api/categories"; // Your backend URL

export const getAllCategories = () => axios.get(BASE_URL);
export const getCategoryById = (id) => axios.get(`${BASE_URL}/${id}`);
export const createCategory = (data) => axios.post(BASE_URL, data);
export const updateCategory = (id, data) => axios.put(`${BASE_URL}/${id}`, data);
export const deleteCategory = (id) => axios.delete(`${BASE_URL}/${id}`);
