// ====================== IMPORTS BỔ SUNG ======================
import {
  Container,
  Box,
  Divider,
  Chip,
  Card,
  CardContent,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableRow,
  Avatar,
} from "@mui/material";
import MKBox from "components/MKBox";
import Grid from "@mui/material/Grid";
import LocalShippingIcon from "@mui/icons-material/LocalShipping";
import CheckCircleIcon from "@mui/icons-material/CheckCircle";
import CancelIcon from "@mui/icons-material/Cancel";
import PendingActionsIcon from "@mui/icons-material/PendingActions";
import MKTypography from "components/MKTypography";
import bgImage from "assets/images/illustrations/illustration-reset.jpg";
import DefaultFooter from "examples/Footers/DefaultFooter";
import footerRoutes from "footer.routes";
import DefaultNavbar from "examples/Navbars/DefaultNavbar";
import routes from "routes";
import { useParams } from "react-router-dom";

// =============================================================

export default function OrderDetailPage() {
  const { id } = useParams();

  console.log("Order ID:", id);

  // gọi API:
  // GET /api/orders/{id}
  const order = {
    orders_id: "7b1f9e70-74d5-4e76-9",
    orders_date: "2026-05-17",
    orders_status: "pending",
    orders_total: 745000,
    shipping_fee: 30000,
    coupon_code: "SAVE10",
    couponship_code: "FREESHIP",
    customer: {
      name: "Nguyễn Văn A",
      email: "nguyenvana@gmail.com",
      phone: "0901234567",
      address: "123 Nguyễn Trãi, Quận 5, TP.HCM",
    },
    items: [
      {
        ordersdtl_id: 1,
        prod_id: 101,
        prod_name: "Atomic Habits",
        quantity: 2,
        price: 250000,
        image: "https://images-na.ssl-images-amazon.com/images/I/91bYsX41DVL.jpg",
      },
      {
        ordersdtl_id: 2,
        prod_id: 102,
        prod_name: "Deep Work",
        quantity: 1,
        price: 215000,
        image: "https://images-na.ssl-images-amazon.com/images/I/71QKQ9mwV7L.jpg",
      },
    ],
  };

  const subtotal = order.items.reduce((sum, item) => sum + item.quantity * item.price, 0);

  const discount = subtotal + order.shipping_fee - order.orders_total;

  const formatCurrency = (value) =>
    new Intl.NumberFormat("vi-VN", {
      style: "currency",
      currency: "VND",
    }).format(value);

  const getStatusChip = (status) => {
    const config = {
      pending: {
        label: "Pending",
        color: "warning",
        icon: <PendingActionsIcon />,
      },
      shipped: {
        label: "Shipped",
        color: "info",
        icon: <LocalShippingIcon />,
      },
      completed: {
        label: "Completed",
        color: "success",
        icon: <CheckCircleIcon />,
      },
      cancelled: {
        label: "Cancelled",
        color: "error",
        icon: <CancelIcon />,
      },
    };

    const item = config[status] || config.pending;

    return <Chip icon={item.icon} label={item.label} color={item.color} sx={{ fontWeight: 600 }} />;
  };

  return (
    <>
      <DefaultNavbar
        routes={routes}
        action={{
          type: "external",
          route: "https://www.creative-tim.com/product/material-kit-react",
          label: "free download",
          color: "default",
        }}
      />
      {/* HERO */}
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
          <Grid container justifyContent="center" sx={{ textAlign: "center" }}>
            <Grid item xs={12} lg={8}>
              <MKTypography variant="h2" color="white" mb={2}>
                Order Details
              </MKTypography>

              <MKTypography variant="body1" color="white" opacity={0.8}>
                Review your order information and purchased products.
              </MKTypography>
            </Grid>
          </Grid>
        </Container>
      </MKBox>

      {/* MAIN CONTENT */}
      <Card
        sx={{
          p: { xs: 2, md: 4 },
          mx: { xs: 2, lg: 3 },
          mt: -5,
          mb: 4,
          borderRadius: 4,
          boxShadow: ({ boxShadows: { xxl } }) => xxl,
        }}
      >
        <Container maxWidth="xl">
          {/* Order Info */}
          <Grid container spacing={3} mb={4}>
            <Grid item xs={12}>
              <Card
                variant="outlined"
                sx={{
                  borderRadius: 3,
                  overflow: "hidden",
                }}
              >
                {/* Decorative top border giống Shopee */}
                <Box />

                <CardContent sx={{ p: 4 }}>
                  <Grid container spacing={4}>
                    {/* LEFT: Shipping Address */}
                    <Grid item xs={12} md={4}>
                      <MKTypography variant="h4" fontWeight="bold" mb={3}>
                        Địa Chỉ Nhận Hàng
                      </MKTypography>

                      <MKTypography variant="h6" fontWeight="bold" mb={1}>
                        {order.customer.name}
                      </MKTypography>

                      <MKTypography variant="body2" color="text.secondary" mb={1}>
                        {order.customer.phone}
                      </MKTypography>

                      <MKTypography variant="body2" color="text.secondary" sx={{ lineHeight: 1.8 }}>
                        {order.customer.address}
                      </MKTypography>
                    </Grid>

                    {/* RIGHT: Order Info */}
                    <Grid item xs={12} md={8}>
                      <Grid container spacing={3}>
                        {/* Order ID */}
                        <Grid item xs={12} md={4}>
                          <MKTypography variant="body2" color="text.secondary">
                            Order ID
                          </MKTypography>
                          <MKTypography
                            fontWeight="bold"
                            sx={{
                              wordBreak: "break-word",
                              fontSize: "0.95rem",
                            }}
                          >
                            {order.orders_id}
                          </MKTypography>
                        </Grid>

                        {/* Order Date */}
                        <Grid item xs={12} md={4}>
                          <MKTypography variant="body2" color="text.secondary">
                            Order Date
                          </MKTypography>
                          <MKTypography fontWeight="bold">{order.orders_date}</MKTypography>
                        </Grid>

                        {/* Status */}
                        <Grid item xs={12} md={4}>
                          <MKTypography variant="body2" color="text.secondary">
                            Status
                          </MKTypography>
                          <Box mt={1}>{getStatusChip(order.orders_status)}</Box>
                        </Grid>
                      </Grid>
                    </Grid>
                  </Grid>
                </CardContent>
              </Card>
            </Grid>
          </Grid>

          {/* Products Table */}
          <Card variant="outlined" sx={{ borderRadius: 3, mb: 4 }}>
            <CardContent>
              <MKTypography variant="h5" fontWeight="bold" mb={3}>
                Ordered Products
              </MKTypography>

              <TableContainer>
                <Table>
                  <TableBody>
                    <TableCell sx={{ fontWeight: 700 }}>Product</TableCell>
                    <TableCell align="center" sx={{ fontWeight: 700 }}>
                      Price
                    </TableCell>
                    <TableCell align="center" sx={{ fontWeight: 700 }}>
                      Quantity
                    </TableCell>
                    <TableCell align="right" sx={{ fontWeight: 700 }}>
                      Total
                    </TableCell>
                    {order.items.map((item) => (
                      <TableRow key={item.ordersdtl_id}>
                        <TableCell>
                          <Box display="flex" alignItems="center" gap={2}>
                            <Avatar
                              src={item.image}
                              variant="rounded"
                              sx={{ width: 60, height: 80 }}
                            />
                            <MKTypography fontWeight="bold">{item.prod_name}</MKTypography>
                          </Box>
                        </TableCell>

                        <TableCell align="center">{formatCurrency(item.price)}</TableCell>

                        <TableCell align="center">{item.quantity}</TableCell>

                        <TableCell align="right" sx={{ fontWeight: 600 }}>
                          {formatCurrency(item.price * item.quantity)}
                        </TableCell>
                      </TableRow>
                    ))}
                  </TableBody>
                </Table>
              </TableContainer>
            </CardContent>
          </Card>

          {/* Order Summary */}
          <Grid container justifyContent="flex-end">
            <Grid item xs={12} md={5} lg={4}>
              <Card
                sx={{
                  p: 3,
                  borderRadius: 3,
                  backgroundColor: "#fafafa",
                }}
              >
                <MKTypography variant="h5" fontWeight="bold" mb={3}>
                  Order Summary
                </MKTypography>

                <Box display="flex" justifyContent="space-between" mb={1.5}>
                  <MKTypography>Subtotal</MKTypography>
                  <MKTypography fontWeight="medium">{formatCurrency(subtotal)}</MKTypography>
                </Box>

                <Box display="flex" justifyContent="space-between" mb={1.5}>
                  <MKTypography>Shipping Fee</MKTypography>
                  <MKTypography fontWeight="medium">
                    {formatCurrency(order.shipping_fee)}
                  </MKTypography>
                </Box>

                <Box display="flex" justifyContent="space-between" mb={1.5}>
                  <MKTypography>Discount</MKTypography>
                  <MKTypography fontWeight="medium" color="success.main">
                    -{formatCurrency(discount)}
                  </MKTypography>
                </Box>

                <Divider sx={{ my: 2 }} />

                <Box display="flex" justifyContent="space-between">
                  <MKTypography variant="h6" fontWeight="bold">
                    Total
                  </MKTypography>
                  <MKTypography variant="h6" fontWeight="bold" color="error.main">
                    {formatCurrency(order.orders_total)}
                  </MKTypography>
                </Box>
              </Card>
            </Grid>
          </Grid>
        </Container>
      </Card>

      {/* FOOTER */}
      <MKBox px={0} mt={6}>
        <DefaultFooter content={footerRoutes} />
      </MKBox>
    </>
  );
}
