// ProductList.jsx
import React, { useEffect, useState } from "react";
import { getAllProducts, deleteProduct } from "../api/productApi";
import { getAllCategories } from "../api/categoryApi";

function ProductList({ refreshTrigger }) {
  const [products, setProducts] = useState([]);
  const [categories, setCategories] = useState({});
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  // Fetch categories and store in a map for quick lookup
  const fetchCategories = async () => {
    try {
      const response = await getAllCategories();
      const catMap = {};
      (response.data || []).forEach((cat) => {
        catMap[cat.id] = cat.name;
      });
      setCategories(catMap);
    } catch (err) {
      console.error("Failed to fetch categories:", err);
    }
  };

  // Fetch all products
  const fetchProducts = async () => {
    setLoading(true);
    setError("");
    try {
      const response = await getAllProducts();
      setProducts(response.data || []);
    } catch (err) {
      console.error("Failed to fetch products:", err);
      setError("Could not load products. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  // Delete a product
  const handleDelete = async (id) => {
    const confirmDelete = window.confirm("Are you sure you want to delete this product?");
    if (!confirmDelete) return;

    try {
      await deleteProduct(id);
      setProducts(products.filter((prod) => prod.id !== id)); // optimistic UI update
    } catch (err) {
      console.error("Failed to delete product:", err);
      setError("Failed to delete product. Please try again.");
    }
  };

  useEffect(() => {
    fetchCategories();
    fetchProducts();
  }, [refreshTrigger]);

  return (
    <div className="max-w-lg mx-auto p-4 bg-white shadow-md rounded-md">
      <h2 className="text-xl font-semibold mb-4">Products</h2>
      {loading ? (
        <p className="text-gray-500">Loading products...</p>
      ) : error ? (
        <p className="text-red-500">{error}</p>
      ) : products.length === 0 ? (
        <p className="text-gray-500">No products found.</p>
      ) : (
        <ul className="space-y-2">
          {products.map((prod) => (
            <li
              key={prod.id}
              className="flex justify-between items-center p-2 border border-gray-200 rounded-md"
            >
              <span>
                {prod.name} - Rs.{prod.price} ({categories[prod.categoryId] || "Unknown Category"})
              </span>
              <button
                onClick={() => handleDelete(prod.id)}
                className="text-red-500 hover:text-red-700 font-semibold"
              >
                Delete
              </button>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default ProductList;
