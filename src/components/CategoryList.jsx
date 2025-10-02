// CategoryList.jsx
import React, { useEffect, useState } from "react";
import { getAllCategories, deleteCategory } from "../api/categoryApi";

function CategoryList({ refreshTrigger }) {
  const [categories, setCategories] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const fetchCategories = async () => {
    setLoading(true);
    setError("");
    try {
      const response = await getAllCategories();
      setCategories(response.data || []);
    } catch (err) {
      console.error("Failed to fetch categories:", err);
      setError("Could not load categories. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (id) => {
    const confirmDelete = window.confirm("Are you sure you want to delete this category?");
    if (!confirmDelete) return;

    try {
      await deleteCategory(id);
      setCategories(categories.filter((cat) => cat.id !== id));
    } catch (err) {
      console.error("Failed to delete category:", err);
      setError("Failed to delete category. Please try again.");
    }
  };

  useEffect(() => {
    fetchCategories();
  }, [refreshTrigger]); // refresh when parent triggers

  return (
    <div className="max-w-md mx-auto mt-6 p-4 bg-white shadow-md rounded-md">
      <h2 className="text-xl font-semibold mb-4">Categories</h2>
      {loading ? (
        <p className="text-gray-500">Loading categories...</p>
      ) : error ? (
        <p className="text-red-500">{error}</p>
      ) : categories.length === 0 ? (
        <p className="text-gray-500">No categories found.</p>
      ) : (
        <ul className="space-y-2">
          {categories.map((cat) => (
            <li
              key={cat.id}
              className="flex justify-between items-center p-2 border border-gray-200 rounded-md"
            >
              <span>{cat.name}</span>
              <button
                onClick={() => handleDelete(cat.id)}
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

export default CategoryList;
