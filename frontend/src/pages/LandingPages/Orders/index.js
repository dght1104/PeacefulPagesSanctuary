import { Container } from "@mui/material";
import Card from "@mui/material/Card";
import Grid from "@mui/material/Grid";
import { Box, Chip, Divider, Button, Stack } from "@mui/material";
import { Link as RouterLink } from "react-router-dom";
// Layout
import DefaultNavbar from "examples/Navbars/DefaultNavbar";
import DefaultFooter from "examples/Footers/DefaultFooter";
import MKBox from "components/MKBox";
import MKTypography from "components/MKTypography";
import { useState, useMemo } from "react";
import Pagination from "@mui/material/Pagination";
// Routes
import routes from "routes";
import footerRoutes from "footer.routes";

// Assets
import bgImage from "assets/images/illustrations/illustration-reset.jpg";
import { orders } from "data/mockOrder";

export default function OrdersPage() {
  // Mock data dựa trên bảng orders

  const formatPrice = (value) => value.toLocaleString("vi-VN") + " ₫";

  const formatDate = (date) => new Date(date).toLocaleDateString("vi-VN");

  const getStatusColor = (status) => {
    switch (status) {
      case "pending":
        return "warning";
      case "shipped":
        return "info";
      case "completed":
        return "success";
      case "cancelled":
        return "error";
      default:
        return "default";
    }
  };

  const getStatusLabel = (status) => {
    switch (status) {
      case "pending":
        return "Pending";
      case "shipped":
        return "Shipped";
      case "completed":
        return "Completed";
      case "cancelled":
        return "Cancelled";
      default:
        return status;
    }
  };
  // =======================
  // Pagination Logic
  // =======================
  const [page, setPage] = useState(1);
  const ordersPerPage = 5;

  const totalPages = Math.ceil(orders.length / ordersPerPage);

  const paginatedOrders = useMemo(() => {
    const startIndex = (page - 1) * ordersPerPage;
    const endIndex = startIndex + ordersPerPage;
    return orders.slice(startIndex, endIndex);
  }, [page, orders]);

  const handlePageChange = (event, value) => {
    setPage(value);
  };
  return (
    <>
      {/* Navbar */}
      <DefaultNavbar routes={routes} />

      {/* Hero Banner */}
      <MKBox
        minHeight="40vh"
        width="100%"
        sx={{
          backgroundImage: ({ functions: { linearGradient, rgba }, palette: { gradients } }) =>
            `${linearGradient(
              rgba(gradients.dark.main, 0.7),
              rgba(gradients.dark.state, 0.7)
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
            lg={8}
            justifyContent="center"
            alignItems="center"
            flexDirection="column"
            sx={{ mx: "auto", textAlign: "center" }}
          >
            <MKTypography variant="h2" color="white" mb={2}>
              My Orders
            </MKTypography>
            <MKTypography variant="body1" color="white" opacity={0.8}>
              Track and manage all your orders.
            </MKTypography>
          </Grid>
        </Container>
      </MKBox>

      {/* Main Content */}
      <Card
        sx={{
          p: { xs: 2, md: 4 },
          mx: { xs: 2, lg: 3 },
          mt: -6,
          mb: 4,
          boxShadow: ({ boxShadows: { xxl } }) => xxl,
        }}
      >
        <Container>
          {/* Title */}
          <MKTypography variant="h3" mb={1}>
            Order History
          </MKTypography>
          <MKTypography variant="body2" color="text" mb={4}>
            You have {orders.length} orders.
          </MKTypography>

          {/* Orders List */}
          <Stack spacing={3}>
            {paginatedOrders.map((order) => (
              <Card
                key={order.orders_id}
                sx={{
                  p: 3,
                  border: "1px solid",
                  borderColor: "grey.200",
                  boxShadow: "none",
                }}
              >
                {/* Header */}
                <Box
                  display="flex"
                  justifyContent="space-between"
                  alignItems={{ xs: "flex-start", md: "center" }}
                  flexDirection={{ xs: "column", md: "row" }}
                  gap={2}
                >
                  <Box>
                    <MKTypography
                      component={RouterLink}
                      to={`/order-detail/${order.orders_id}`}
                      variant="h6"
                      sx={{
                        textDecoration: "none",
                        color: "primary.main",
                        fontWeight: "bold",
                        "&:hover": {
                          textDecoration: "underline",
                        },
                      }}
                    >
                      Order #{order.orders_id}
                    </MKTypography>
                    <MKTypography variant="body2" color="text">
                      Placed on {formatDate(order.orders_date)}
                    </MKTypography>
                  </Box>

                  <Chip
                    label={getStatusLabel(order.orders_status)}
                    color={getStatusColor(order.orders_status)}
                    size="small"
                  />
                </Box>

                <Divider sx={{ my: 2 }} />

                {/* Order Info */}
                <Grid container spacing={2}>
                  <Grid item xs={12} sm={6} md={2.4}>
                    <MKTypography variant="caption" color="text">
                      Order Total
                    </MKTypography>
                    <MKTypography variant="body1" fontWeight="bold">
                      {formatPrice(order.orders_total)}
                    </MKTypography>
                  </Grid>

                  <Grid item xs={12} sm={6} md={2.4}>
                    <MKTypography variant="caption" color="text">
                      Shipping Fee
                    </MKTypography>
                    <MKTypography variant="body1">{formatPrice(order.shipping_fee)}</MKTypography>
                  </Grid>

                  <Grid item xs={12} sm={6} md={2.4}>
                    <MKTypography variant="caption" color="text">
                      Items
                    </MKTypography>
                    <MKTypography variant="body1">{order.item_count}</MKTypography>
                  </Grid>

                  <Grid item xs={12} sm={6} md={2.4}>
                    <MKTypography variant="caption" color="text">
                      Coupon
                    </MKTypography>
                    <MKTypography variant="body1">{order.coupon_code || "N/A"}</MKTypography>
                  </Grid>

                  <Grid item xs={12} sm={6} md={2.4}>
                    <MKTypography variant="caption" color="text">
                      Shipping Coupon
                    </MKTypography>
                    <MKTypography variant="body1">{order.couponship_code || "N/A"}</MKTypography>
                  </Grid>
                </Grid>

                <Divider sx={{ my: 2 }} />

                {/* Actions */}
                <Box display="flex" gap={1.5} justifyContent="flex-end" flexWrap="wrap" mt={1}>
                  {/* Cancel Order */}
                  {order.orders_status === "pending" && (
                    <Button
                      variant="outlined"
                      color="error"
                      size="small"
                      sx={{
                        borderRadius: "10px",
                        px: 2.5,
                        py: 0.8,
                        textTransform: "none",
                        fontWeight: 600,
                        minWidth: 130,
                      }}
                    >
                      Cancel Order
                    </Button>
                  )}
                </Box>
              </Card>
            ))}
          </Stack>
          {/* Empty State */}
          {orders.length === 0 && (
            <Box textAlign="center" py={8}>
              <MKTypography variant="h5" mb={2}>
                You have not placed any orders yet.
              </MKTypography>
              <Button variant="contained">Start Shopping</Button>
            </Box>
          )}
          {/* Pagination UI */}
          {orders.length > ordersPerPage && (
            <Box display="flex" justifyContent="center" mt={5}>
              <Pagination
                count={totalPages}
                page={page}
                onChange={handlePageChange}
                color="primary"
                shape="rounded"
                size="large"
              />
            </Box>
          )}
        </Container>
      </Card>

      {/* Footer */}
      <MKBox pt={6}>
        <DefaultFooter content={footerRoutes} />
      </MKBox>
    </>
  );
}
