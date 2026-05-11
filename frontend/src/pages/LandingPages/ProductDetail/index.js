import { useMemo, useState } from "react";
import { useParams } from "react-router-dom";
import { BookData } from "data/mockBooks";
import MKBookCard from "pages/Presentation/sections/BookCard";
import { useNavigate } from "react-router-dom";
import DefaultNavbar from "examples/Navbars/DefaultNavbar";
import DefaultFooter from "examples/Footers/DefaultFooter";
import MKBox from "components/MKBox";
import MKTypography from "components/MKTypography";
import routes from "routes";
import bgImage from "assets/images/illustrations/illustration-reset.jpg";
import Grid from "@mui/material/Grid";
import footerRoutes from "footer.routes";
import { Container } from "@mui/material";
import MKButton from "components/MKButton";
import ShoppingCartOutlinedIcon from "@mui/icons-material/ShoppingCartOutlined";
export default function ProductDetail() {
  const { id } = useParams();

  // Trong component
  const navigate = useNavigate();
  // Tìm sản phẩm theo id từ URL
  const book = useMemo(() => {
    return BookData.find((item) => item.id === Number(id));
  }, [id]);

  // Không tìm thấy sản phẩm
  if (!book) {
    return <div>Không tìm thấy sản phẩm với ID: {id}</div>;
  }

  // Sản phẩm liên quan (cùng genre, khác id hiện tại)
  const relatedBooks = BookData.filter(
    (item) => item.id !== book.id && item.genre === book.genre
  ).slice(0, 4);

  // Danh sách ảnh sản phẩm
  const productImages = [
    book.coverImage ||
      "https://images.unsplash.com/photo-1544947950-fa07a98d237f?auto=format&fit=crop&w=800&q=80",
    "https://images.unsplash.com/photo-1512820790803-83ca734da794?auto=format&fit=crop&w=800&q=80",
    "https://images.unsplash.com/photo-1524995997946-a1c2e315a42f?auto=format&fit=crop&w=800&q=80",
  ];

  // Ảnh đang được chọn
  const [selectedImage, setSelectedImage] = useState(productImages[0]);

  const [added, setAdded] = useState(false);

  const handleAddToCart = () => {
    setAdded(true);

    // // Gọi hàm từ component cha nếu có
    // onAddToCart?.(book);

    // Sau 3 giây đổi lại chữ "Add to Cart"
    setTimeout(() => {
      setAdded(false);
    }, 3000);
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
            lg={8}
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
              Work with an amazing design
            </MKTypography>
            <MKTypography variant="body1" color="white" opacity={0.8} mt={0} mb={0}>
              We&apos;re constantly trying to express ourselves and actualize our dreams. If you
              have the opportunity to play this game
            </MKTypography>
          </Grid>
        </Container>
      </MKBox>

      {/* PRODUCT DETAIL PAGE */}
      <MKBox
        sx={{
          maxWidth: "1400px",
          mx: "auto",
          px: 3,
          py: 4,
        }}
      >
        <MKBox
          sx={{
            bgcolor: "#fff",
            borderRadius: 2,
            p: 4,
            boxShadow: "0 2px 12px rgba(0,0,0,0.08)",
            display: "grid",
            gridTemplateColumns: {
              xs: "1fr",
              md: "420px 1fr",
            },
            gap: 5,
          }}
        >
          {/* LEFT - PRODUCT IMAGE */}
          <MKBox>
            {/* MAIN IMAGE */}
            <MKBox
              component="img"
              src={selectedImage}
              alt={book.title}
              sx={{
                width: "100%",
                height: 520,
                objectFit: "cover",
                borderRadius: 2,
                border: "1px solid #eee",
              }}
            />

            {/* THUMBNAILS */}
            <MKBox
              sx={{
                display: "flex",
                gap: 1,
                mt: 2,
              }}
            >
              {productImages.map((img, index) => (
                <MKBox
                  key={index}
                  component="img"
                  src={img}
                  alt={`Thumbnail ${index + 1}`}
                  onClick={() => setSelectedImage(img)}
                  sx={{
                    width: 72,
                    height: 72,
                    objectFit: "cover",
                    borderRadius: 1,
                    border: selectedImage === img ? "2px solid #ee4d2d" : "1px solid #ddd",
                    cursor: "pointer",
                    transition: "all 0.2s ease",
                    opacity: selectedImage === img ? 1 : 0.8,
                    "&:hover": {
                      borderColor: "#ee4d2d",
                      opacity: 1,
                    },
                  }}
                />
              ))}
            </MKBox>
          </MKBox>

          {/* RIGHT - PRODUCT INFO */}
          <MKBox
            sx={{
              display: "flex",
              flexDirection: "column",
              gap: 3, // chia đều khoảng cách giữa tất cả các section
            }}
          >
            {/* TITLE */}
            <MKTypography
              variant="h4"
              fontWeight="700"
              gutterBottom
              sx={{
                mb: 3, // padding-bottom
              }}
            >
              {book.title}
            </MKTypography>

            {/* AUTHOR + CATEGORY */}
            <MKBox
              sx={{
                display: "flex",
                alignItems: "center",
                gap: 4,
                flexWrap: "wrap",
                mb: 3,
              }}
            >
              {/* AUTHOR */}
              <MKTypography variant="body1" color="text.secondary">
                Tác giả: <strong>{book.author}</strong>
              </MKTypography>

              {/* CATEGORY */}
              <MKTypography variant="body1" color="text.secondary">
                Danh mục: <strong>{book.genre}</strong>
              </MKTypography>
            </MKBox>

            {/* RATING - REVIEWS - SOLD */}
            <MKBox display="flex" alignItems="center" flexWrap="wrap" gap={2} mb={3} mr={3}>
              {/* Rating */}
              <MKBox display="flex" alignItems="center" gap={0.5} mr={3}>
                <MKTypography variant="body1" fontWeight="bold" color="#ee4d2d">
                  {book.rating}{" "}
                </MKTypography>
                <MKTypography color="#ee4d2d">★</MKTypography>
              </MKBox>
              <MKTypography color="text.secondary">|</MKTypography>
              {/* Reviews */}
              <MKTypography variant="body1" mr={3}>
                <strong>{book.reviews?.toLocaleString("vi-VN")}</strong>
                <MKTypography component="span" variant="body2" color="text.secondary" ml={0.5}>
                  đánh giá{" "}
                </MKTypography>
              </MKTypography>
              <MKTypography color="text.secondary">|</MKTypography>
              {/* Sold */}
              <MKTypography variant="body1" mr={3}>
                Đã bán{" "}
                <strong>
                  {book.sold >= 1000
                    ? `${(book.sold / 1000).toFixed(1).replace(".", ",")}k`
                    : book.sold}
                </strong>
              </MKTypography>
            </MKBox>

            {/* PRICE */}
            <MKBox
              sx={{
                bgcolor: "#fafafa",
                p: 3,
                borderRadius: 2,
                mb: 3,
                display: "flex",
                alignItems: "center",
                gap: 2,
                flexWrap: "wrap",
              }}
            >
              {/* Giá gốc */}
              <MKTypography
                variant="h6"
                color="text.secondary"
                sx={{
                  textDecoration: "line-through",
                }}
              >
                ${book.originalPrice}
              </MKTypography>

              {/* Giá sau giảm */}
              <MKTypography variant="h3" fontWeight="bold" color="#ee4d2d">
                ${book.price}
              </MKTypography>

              {/* Badge giảm giá */}
              <MKBox
                sx={{
                  px: 1.2,
                  py: 0.4,
                  bgcolor: "#fff0ec",
                  color: "#ee4d2d",
                  borderRadius: 1,
                  fontSize: "0.875rem",
                  fontWeight: 700,
                }}
              >
                -{book.discount}%
              </MKBox>
            </MKBox>

            {/* QUANTITY */}
            <MKBox display="flex" alignItems="center" gap={2} mb={4}>
              <MKTypography>Số lượng</MKTypography>

              <MKBox
                sx={{
                  display: "flex",
                  alignItems: "center",
                  border: "1px solid #ddd",
                  borderRadius: 1,
                }}
              >
                <MKButton sx={{ minWidth: 40 }}>-</MKButton>
                <MKTypography px={3}>1</MKTypography>
                <MKButton sx={{ minWidth: 40 }}>+</MKButton>
              </MKBox>

              <MKTypography color="text.secondary">{book.stock} sản phẩm có sẵn</MKTypography>
            </MKBox>

            {/* ACTION MKButtonS */}
            <MKBox
              sx={{
                display: "flex",
                justifyContent: "center", // căn giữa theo chiều ngang
                alignItems: "center",
                gap: 2,
                mt: 3,
                flexWrap: "wrap", // responsive trên mobile
              }}
            >
              <MKButton
                variant="contained"
                size="large"
                disableElevation
                onClick={handleAddToCart}
                startIcon={<ShoppingCartOutlinedIcon />}
                sx={{
                  px: 4,
                  py: 1.5,
                  borderRadius: "10px",
                  fontSize: "0.9rem",
                  textTransform: "none",
                  bgcolor: "#A3C1FF !important",
                  color: "#1E3A5F",
                  fontWeight: 600,
                  boxShadow: "none !important",

                  "&:hover": {
                    bgcolor: "#8FB3FF !important",
                    boxShadow: "none !important",
                  },
                  "&:focus": {
                    bgcolor: "#A3C1FF !important",
                    boxShadow: "none !important",
                  },
                  "&:active": {
                    bgcolor: "#A3C1FF !important",
                    boxShadow: "none !important",
                  },
                  "&.Mui-focusVisible": {
                    bgcolor: "#A3C1FF !important",
                    boxShadow: "none !important",
                  },
                }}
              >
                {added ? "Added!" : "Add to Cart"}
              </MKButton>

              <MKButton
                variant="contained"
                size="large"
                disableElevation
                onClick={() => navigate("/cart")}
                startIcon={<ShoppingCartOutlinedIcon />}
                sx={{
                  px: 4,
                  py: 1.5,
                  borderRadius: "10px",
                  fontSize: "0.9rem",
                  textTransform: "none",
                  bgcolor: "#ff6060",
                  color: "#ffffff",
                  fontWeight: 600,
                  boxShadow: "none",

                  // Giữ nguyên màu khi hover
                  "&:hover": {
                    bgcolor: "#ffffff",
                    boxShadow: "none",
                  },
                }}
              >
                Buy Now
              </MKButton>
            </MKBox>
          </MKBox>
        </MKBox>

        {/* PRODUCT DESCRIPTION SECTION */}
        <MKBox
          sx={{
            bgcolor: "#fff",
            mt: 4,
            p: 4,
            borderRadius: 2,
            boxShadow: "0 2px 12px rgba(0,0,0,0.08)",
          }}
        >
          <MKTypography variant="h5" fontWeight="bold" mb={3}>
            Chi tiết sản phẩm
          </MKTypography>

          <MKTypography sx={{ lineHeight: 2 }}>{book.description}</MKTypography>
        </MKBox>

        <MKBox
          sx={{
            bgcolor: "#fff",
            mt: 4,
            p: 4,
            borderRadius: 2,
            boxShadow: "0 2px 12px rgba(0,0,0,0.08)",
          }}
        >
          {/* RELATED PRODUCTS */}
          <MKBox>
            <MKTypography variant="h4" fontWeight="bold" mb={3}>
              Sản phẩm liên quan
            </MKTypography>

            {relatedBooks.length > 0 ? (
              <Grid container spacing={3}>
                {relatedBooks.map((relatedBook) => (
                  <Grid item xs={12} sm={6} md={4} lg={3} key={relatedBook.id}>
                    <MKBookCard id={relatedBook.id} />
                  </Grid>
                ))}
              </Grid>
            ) : (
              <MKTypography color="text.secondary">Không có sản phẩm liên quan.</MKTypography>
            )}
          </MKBox>
        </MKBox>
      </MKBox>

      <MKBox px={0} mt={4}>
        <DefaultFooter content={footerRoutes} />
      </MKBox>
    </>
  );
}
