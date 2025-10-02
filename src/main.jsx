// main.jsx or index.jsx (whichever you are using as entry)
import React from "react";
import { createRoot } from "react-dom/client";
import App from "./App.jsx";
import "bootstrap/dist/css/bootstrap.min.css"; // <-- Import Bootstrap CSS
import './index.css'; // optional if you have custom styles

createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
