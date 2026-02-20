import { useEffect, useState } from "react";
import api from "../api/axios";
import { Link } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

function ProductList() {
  const [products, setProducts] = useState([]);
  const { user } = useAuth();

  useEffect(() => {
    api.get("/products")
      .then(res => setProducts(res.data.data.content))
      .catch(err => console.error(err));
  }, []);

  const addToCart = (id) => {
    if (!user) return alert("Login required");
    api.post("/cart/add", null, {
      params: { email: user.email, productId: id, quantity: 1 }
    }).then(() => alert("Added to cart"));
  };

  return (
    <div>
      <h1>Products</h1>
      <Link to="/cart">Cart</Link>
      {products.map(p => (
        <div key={p.id}>
          <h3>{p.name}</h3>
          <p>Price: {p.price}</p>
          <button onClick={() => addToCart(p.id)}>Add</button>
        </div>
      ))}
    </div>
  );
}

export default ProductList;