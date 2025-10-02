// src/components/ProductForm.jsx
import React, { useState, useEffect } from "react";
import { createProduct } from "../api/productApi";
import { getAllCategories } from "../api/categoryApi";

function ProductForm({ onSuccess }) {
  const [name, setName] = useState("");
  const [price, setPrice] = useState("");
  const [categoryId, setCategoryId] = useState("");
  const [categories, setCategories] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  // Fetch categories on mount
  const fetchCategories = async () => {
    try {
      const response = await getAllCategories();
      const cats = Array.isArray(response.data) ? response.data : [];
      setCategories(cats);
      if (cats.length > 0) setCategoryId(cats[0].id);
    } catch (err) {
      console.error(err);
      setError("Failed to load categories.");
    }
  };

  useEffect(() => {
    fetchCategories();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!name.trim() || !price || !categoryId) {
      setError("Please fill in all fields.");
      return;
    }

    setLoading(true);
    setError("");

    try {
      await createProduct({ name, price: parseFloat(price), categoryId });
      setName("");
      setPrice("");
      setCategoryId(categories.length > 0 ? categories[0].id : "");
      onSuccess(); // refresh product list in parent
    } catch (err) {
      console.error(err);
      setError("Failed to add product. Try again.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="max-w-md mx-auto p-4 bg-white shadow-md rounded-md mb-6">
      <h3 className="text-lg font-semibold mb-4">Add Product</h3>
      {error && <p className="text-red-500 mb-2">{error}</p>}
      <form onSubmit={handleSubmit} className="flex flex-col gap-3">
        <input
          type="text"
          value={name}
          onChange={(e) => setName(e.target.value)}
          placeholder="Product Name"
          className="p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
          required
        />
        <input
          type="number"
          value={price}
          onChange={(e) => setPrice(e.target.value)}
          placeholder="Price"
          className="p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
          required
        />
        <select
          value={categoryId}
          onChange={(e) => setCategoryId(e.target.value)}
          className="p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
          required
        >
          {categories.map((cat) => (
            <option key={cat.id} value={cat.id}>
              {cat.name}
            </option>
          ))}
        </select>
        <button
          type="submit"
          disabled={loading}
          className={`p-2 rounded-md text-white font-semibold ${
            loading ? "bg-gray-400 cursor-not-allowed" : "bg-blue-500 hover:bg-blue-600"
          }`}
        >
          {loading ? "Adding..." : "Add Product"}
        </button>
      </form>
    </div>
  );
}

export default ProductForm;
