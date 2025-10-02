// CategoryForm.jsx
import React, { useState } from "react";
import { createCategory } from "../api/categoryApi";

function CategoryForm({ onSuccess }) {
  const [name, setName] = useState("");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!name.trim()) {
      setError("Category name cannot be empty");
      return;
    }

    setLoading(true);
    setError("");

    try {
      await createCategory({ name });
      setName("");       // reset input
      onSuccess();       // refresh App state
    } catch (err) {
      console.error(err);
      setError("Failed to create category. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="max-w-md mx-auto p-4 bg-white shadow-md rounded-md">
      <h2 className="text-xl font-semibold mb-4">Add New Category</h2>
      <form onSubmit={handleSubmit} className="flex flex-col gap-3">
        <input
          type="text"
          value={name}
          onChange={(e) => setName(e.target.value)}
          placeholder="Enter category name"
          className="p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
        />
        {error && <p className="text-red-500 text-sm">{error}</p>}
        <button
          type="submit"
          disabled={loading}
          className={`p-2 rounded-md text-white font-semibold ${
            loading ? "bg-gray-400 cursor-not-allowed" : "bg-blue-500 hover:bg-blue-600"
          }`}
        >
          {loading ? "Adding..." : "Add Category"}
        </button>
      </form>
    </div>
  );
}

export default CategoryForm;
