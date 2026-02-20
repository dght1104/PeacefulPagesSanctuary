import { useEffect, useState } from "react";
import api from "../api/axios";
import { useAuth } from "../context/AuthContext";
import { Link } from "react-router-dom";

function CartPage() {
  const { user } = useAuth();
  const [items, setItems] = useState([]);

  useEffect(() => {
    if (user) {
      api.get("/cart", { params: { email: user.email } })
        .then(res => setItems(res.data.data))
        .catch(err => console.error(err));
    }
  }, [user]);

  return (
    <div>
      <h1>Cart</h1>
      <Link to="/">Back</Link>
      {items.map(i => (
        <div key={i.id}>
          <p>{i.product.name} x {i.quantity}</p>
        </div>
      ))}
      <Link to="/checkout">Proceed to Checkout</Link>
    </div>
  );
}

export default CartPage;