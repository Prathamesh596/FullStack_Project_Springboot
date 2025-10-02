// App.jsx
import React, { useState } from "react";
import CategoryList from "./components/CategoryList.jsx";
import CategoryForm from "./components/CategoryForm.jsx";
import ProductList from "./components/ProductList.jsx";
import ProductForm from "./components/ProductForm.jsx";

function App() {
  const [refreshKey, setRefreshKey] = useState(0);

  // Increment refreshKey to trigger reload in child components
  const handleRefresh = () => {
    setRefreshKey((prev) => prev + 1);
  };

  return (
    <div className="max-w-5xl mx-auto p-6">
      <h1 className="text-3xl font-bold text-center mb-8">
        Inventory Management
      </h1>

      {/* Category Section */}
      <div className="grid md:grid-cols-2 gap-6 mb-10">
        <CategoryForm onSuccess={handleRefresh} />
        <CategoryList refreshTrigger={refreshKey} />
      </div>

      {/* Product Section */}
      <div className="grid md:grid-cols-2 gap-6">
        <ProductForm onSuccess={handleRefresh} />
        <ProductList refreshTrigger={refreshKey} />
      </div>
    </div>
  );
}

export default App;
