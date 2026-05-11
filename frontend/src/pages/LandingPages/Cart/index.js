/*
=========================================================
* Material Kit 2 React - v2.1.0
=========================================================
*/

// Material Kit 2 React components
import MKBox from "components/MKBox";
import MKTypography from "components/MKTypography";
import { Link as RouterLink } from "react-router-dom";

import { useMemo, useState } from "react";
import {
  Container,
  Grid,
  Card,
  Checkbox,
  Typography,
  Button,
  IconButton,
  Divider,
  Box,
} from "@mui/material";
import DeleteIcon from "@mui/icons-material/Delete";
// Material Kit 2 React examples
import DefaultNavbar from "examples/Navbars/DefaultNavbar";
import DefaultFooter from "examples/Footers/DefaultFooter";

// Routes
import routes from "routes";
import footerRoutes from "footer.routes";

// Image
import bgImage from "assets/images/illustrations/illustration-reset.jpg";
const initialCart = [
  {
    id: 1,
    title: "Atomic Habits",
    author: "James Clear",
    price: 18.99,
    quantity: 2,
    image:
      "https://images.unsplash.com/photo-1544947950-fa07a98d237f?auto=format&fit=crop&w=800&q=80",
    selected: true,
  },
  {
    id: 2,
    title: "Deep Learning",
    author: "Ian Goodfellow",
    price: 45.0,
    quantity: 1,
    image:
      "https://images.unsplash.com/photo-1512820790803-83ca734da794?auto=format&fit=crop&w=800&q=80",
    selected: false,
  },
  {
    id: 3,
    title: "Clean Code",
    author: "Robert C. Martin",
    price: 24.99,
    quantity: 1,
    image:
      "https://images.unsplash.com/photo-1524995997946-a1c2e315a42f?auto=format&fit=crop&w=800&q=80",
    selected: true,
  },
];

export default function Cart() {
  const [cartItems, setCartItems] = useState(initialCart);

  // Toggle chọn từng item
  const handleSelectItem = (id) => {
    setCartItems((prev) =>
      prev.map((item) => (item.id === id ? { ...item, selected: !item.selected } : item))
    );
  };

  // Select all
  const handleSelectAll = (checked) => {
    setCartItems((prev) =>
      prev.map((item) => ({
        ...item,
        selected: checked,
      }))
    );
  };

  // Tăng giảm số lượng
  const updateQuantity = (id, delta) => {
    setCartItems((prev) =>
      prev.map((item) =>
        item.id === id
          ? {
              ...item,
              quantity: Math.max(1, item.quantity + delta),
            }
          : item
      )
    );
  };

  // Xóa item
  const removeItem = (id) => {
    setCartItems((prev) => prev.filter((item) => item.id !== id));
  };

  // Derived values
  const selectedItems = cartItems.filter((item) => item.selected);

  const allSelected = cartItems.length > 0 && cartItems.every((item) => item.selected);

  const selectedCount = selectedItems.length;

  const subtotal = useMemo(
    () => selectedItems.reduce((sum, item) => sum + item.price * item.quantity, 0),
    [selectedItems]
  );

  const shipping = subtotal > 50 ? 0 : subtotal > 0 ? 5 : 0;
  const total = subtotal + shipping;

  return (
    <>
      <DefaultNavbar routes={routes} transparent light />

      {/* Hero Section */}
      <MKBox
        minHeight="50vh"
        width="100%"
        sx={{
          backgroundImage: ({ functions: { linearGradient, rgba }, palette: { gradients } }) =>
            `${linearGradient(
              rgba(gradients.dark.main, 0.6),
              rgba(gradients.dark.state, 0.6)
            )}, url(${bgImage})`,
          backgroundSize: "cover",
          backgroundPosition: "center",
          display: "grid",
          placeItems: "center",
        }}
      >
        <Container>
          <Grid
            container
            item
            xs={12}
            lg={7}
            justifyContent="center"
            alignItems="center"
            flexDirection="column"
            sx={{ mx: "auto", textAlign: "center" }}
          >
            <MKTypography
              variant="h1"
              color="white"
              sx={({ breakpoints, typography: { size } }) => ({
                [breakpoints.down("md")]: {
                  fontSize: size["3xl"],
                },
              })}
            >
              Cart
            </MKTypography>

            <MKTypography variant="body1" color="white" opacity={0.8} mt={1}>
              Have questions or feedback? We will love to hear from you. Send us a message and we’ll
              respond as soon as possible.
            </MKTypography>
          </Grid>
        </Container>
      </MKBox>

      {/* Contact Form Card */}
      <Card
        sx={{
          p: 2,
          mx: { xs: 2, lg: 3 },
          mt: -5,
          mb: 4,
          boxShadow: ({ boxShadows: { xxl } }) => xxl,
        }}
      >
        <Box sx={{ py: 3, width: "100%", px: 5 }}>
          <Typography variant="h3" fontWeight="bold" mb={1}>
            Shopping Cart ({cartItems.length} items)
          </Typography>

          <Typography variant="body2" color="text.secondary" mb={4}>
            Review your selected books before checkout.
          </Typography>

          <Grid container spacing={4}>
            {/* LEFT SIDE */}
            <Grid item xs={12} lg={8}>
              <Card sx={{ p: 3, borderRadius: 3 }}>
                {/* Select All */}
                <Box display="flex" alignItems="center" justifyContent="space-between" mb={3}>
                  <Box display="flex" alignItems="center">
                    <Checkbox
                      checked={allSelected}
                      onChange={(e) => handleSelectAll(e.target.checked)}
                    />
                    <Typography fontWeight="medium">Select All</Typography>
                  </Box>

                  <Typography variant="body2" color="text.secondary">
                    {selectedCount} selected
                  </Typography>
                </Box>

                <Divider sx={{ mb: 3 }} />

                {/* Cart Items */}
                {cartItems.map((item) => (
                  <Card
                    key={item.id}
                    variant="outlined"
                    sx={{
                      p: 2,
                      mb: 2,
                      borderRadius: 3,
                    }}
                  >
                    <Grid container spacing={2} alignItems="center">
                      {/* Checkbox */}
                      <Grid item>
                        <Checkbox
                          checked={item.selected}
                          onChange={() => handleSelectItem(item.id)}
                        />
                      </Grid>

                      {/* Image */}
                      <Grid item>
                        <Box
                          component="img"
                          src={item.image}
                          alt={item.title}
                          sx={{
                            width: 90,
                            height: 130,
                            objectFit: "cover",
                            borderRadius: 2,
                          }}
                        />
                      </Grid>

                      {/* Info */}
                      <Grid item xs>
                        <Typography variant="h6" fontWeight="bold">
                          {item.title}
                        </Typography>

                        <Typography variant="body1" fontWeight="bold" sx={{ mt: 1 }}>
                          ${item.price.toFixed(2)}
                        </Typography>
                      </Grid>

                      {/* Quantity */}
                      <Grid item>
                        <Box
                          sx={{
                            display: "flex",
                            alignItems: "center",
                            border: "1px solid",
                            borderColor: "grey.300",
                            borderRadius: 1,
                            overflow: "hidden",
                            width: "fit-content",
                            bgcolor: "white",
                          }}
                        >
                          {/* Minus */}
                          <Box
                            component="button"
                            onClick={() => updateQuantity(item.id, -1)}
                            disabled={item.quantity <= 1}
                            sx={{
                              width: 36,
                              height: 36,
                              border: "none",
                              borderRight: "1px solid",
                              borderColor: "grey.300",
                              bgcolor: "transparent",
                              fontSize: "1.25rem",
                              cursor: item.quantity <= 1 ? "not-allowed" : "pointer",
                              color: item.quantity <= 1 ? "grey.400" : "text.primary",
                              "&:hover": {
                                bgcolor: item.quantity <= 1 ? "transparent" : "grey.100",
                              },
                            }}
                          >
                            −
                          </Box>

                          {/* Quantity */}
                          <Box
                            sx={{
                              width: 50,
                              height: 36,
                              display: "flex",
                              alignItems: "center",
                              justifyContent: "center",
                              fontWeight: 500,
                              fontSize: "0.95rem",
                            }}
                          >
                            {item.quantity}
                          </Box>

                          {/* Plus */}
                          <Box
                            component="button"
                            onClick={() => updateQuantity(item.id, 1)}
                            sx={{
                              width: 36,
                              height: 36,
                              border: "none",
                              borderLeft: "1px solid",
                              borderColor: "grey.300",
                              bgcolor: "transparent",
                              fontSize: "1.25rem",
                              cursor: "pointer",
                              color: "text.primary",
                              "&:hover": {
                                bgcolor: "grey.100",
                              },
                            }}
                          >
                            +
                          </Box>
                        </Box>
                      </Grid>

                      {/* Subtotal */}
                      <Grid item>
                        <Typography fontWeight="bold">
                          ${(item.price * item.quantity).toFixed(2)}
                        </Typography>
                      </Grid>

                      {/* Remove */}
                      <Grid item>
                        <IconButton color="error" onClick={() => removeItem(item.id)}>
                          <DeleteIcon />
                        </IconButton>
                      </Grid>
                    </Grid>
                  </Card>
                ))}

                <Button
                  component={RouterLink}
                  to="/product" // đổi thành route trang Product của bạn
                  sx={{ mt: 2 }}
                >
                  {" "}
                  ← Continue Shopping
                </Button>
              </Card>
            </Grid>

            {/* RIGHT SIDE - SUMMARY */}
            <Grid item xs={12} lg={4}>
              <Card
                sx={{
                  p: 3,
                  borderRadius: 3,
                  position: "sticky",
                  top: 100,
                }}
              >
                <Typography variant="h5" fontWeight="bold" mb={3}>
                  Order Summary
                </Typography>

                <Box display="flex" justifyContent="space-between" mb={1}>
                  <Typography color="text.secondary">Selected Items</Typography>
                  <Typography>{selectedCount}</Typography>
                </Box>

                <Box display="flex" justifyContent="space-between" mb={1}>
                  <Typography color="text.secondary">Subtotal</Typography>
                  <Typography>${subtotal.toFixed(2)}</Typography>
                </Box>

                <Box display="flex" justifyContent="space-between" mb={1}>
                  <Typography color="text.secondary">Shipping</Typography>
                  <Typography>{shipping === 0 ? "Free" : `$${shipping.toFixed(2)}`}</Typography>
                </Box>

                <Divider sx={{ my: 2 }} />

                <Box display="flex" justifyContent="space-between" mb={3}>
                  <Typography variant="h6" fontWeight="bold">
                    Total
                  </Typography>
                  <Typography variant="h6" fontWeight="bold">
                    ${total.toFixed(2)}
                  </Typography>
                </Box>

                <Button
                  fullWidth
                  variant="contained"
                  size="large"
                  disabled={selectedCount === 0}
                  component={RouterLink}
                  to="/checkout"
                >
                  Checkout
                </Button>
              </Card>
            </Grid>
          </Grid>
        </Box>
      </Card>
      <MKBox px={1} mt={4}>
        <DefaultFooter content={footerRoutes} />
      </MKBox>
    </>
  );
}
