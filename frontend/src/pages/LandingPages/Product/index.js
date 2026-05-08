import { useState, useMemo, useEffect } from "react";

// MUI
import {
  Container,
  Box,
  Chip,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
  TextField,
  Slider,
  Breadcrumbs,
  Link,
  Divider,
  Pagination,
} from "@mui/material";

// Layout
import DefaultNavbar from "examples/Navbars/DefaultNavbar";
import DefaultFooter from "examples/Footers/DefaultFooter";
import MKBox from "components/MKBox";
import MKTypography from "components/MKTypography";

// Data + Component
import BookCard from "components/MKBookCard";
import { BookData } from "data/mockBooks";
import Card from "@mui/material/Card";

// Routes
import routes from "routes";
import footerRoutes from "footer.routes";
import bgImage from "assets/images/illustrations/illustration-reset.jpg";
import Grid from "@mui/material/Grid";
export default function ProductPage() {
  const [sortBy, setSortBy] = useState("featured");
  const [filterGenre, setFilterGenre] = useState("All");
  const [search, setSearch] = useState("");
  const [priceRange, setPriceRange] = useState([0, 100]);
  const [supplierFilter, setSupplierFilter] = useState("All");

  const suppliers = ["All", "Tiki", "Fahasa", "Shopee", "Amazon"];

  const [page, setPage] = useState(1);
  const itemsPerPage = 10;

  // RESET PAGE WHEN FILTER CHANGE
  useEffect(() => {
    setPage(1);
  }, [search, filterGenre, priceRange, supplierFilter, sortBy]);

  // FILTER
  const filteredBooks = useMemo(() => {
    let result = [...BookData];

    // SEARCH
    result = result.filter((b) => b.title.toLowerCase().includes(search.toLowerCase()));

    // GENRE
    if (filterGenre !== "All") {
      result = result.filter((b) => b.genre === filterGenre);
    }

    // PRICE
    result = result.filter((b) => b.price >= priceRange[0] && b.price <= priceRange[1]);

    // SUPPLIER
    if (supplierFilter !== "All") {
      result = result.filter((b) => b.supplier === supplierFilter);
    }

    // SORT
    switch (sortBy) {
      case "price-asc":
        result.sort((a, b) => a.price - b.price);
        break;
      case "price-desc":
        result.sort((a, b) => b.price - a.price);
        break;
      case "title-asc":
        result.sort((a, b) => a.title.localeCompare(b.title));
        break;
      case "rating":
        result.sort((a, b) => b.rating - a.rating);
        break;
      default:
        break;
    }

    return result;
  }, [sortBy, filterGenre, search, priceRange, supplierFilter]);

  // PAGINATION
  const paginatedBooks = useMemo(() => {
    const start = (page - 1) * itemsPerPage;
    return filteredBooks.slice(start, start + itemsPerPage);
  }, [filteredBooks, page]);

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
            <MKTypography variant="h6" color="white" mt={4} mb={1}>
              Find us on
            </MKTypography>
            <MKBox display="flex" justifyContent="center" alignItems="center">
              <MKTypography component="a" variant="body1" color="white" href="#" mr={3}>
                <i className="fab fa-facebook" />
              </MKTypography>
              <MKTypography component="a" variant="body1" color="white" href="#" mr={3}>
                <i className="fab fa-instagram" />
              </MKTypography>
              <MKTypography component="a" variant="body1" color="white" href="#" mr={3}>
                <i className="fab fa-twitter" />
              </MKTypography>
              <MKTypography component="a" variant="body1" color="white" href="#">
                <i className="fab fa-google-plus" />
              </MKTypography>
            </MKBox>
          </Grid>
        </Container>
      </MKBox>

      {/* PAGE */}
      <Card
        sx={{
          p: 2,
          mx: { xs: 2, lg: 3 },
          mt: -5,
          mb: 4,
          boxShadow: ({ boxShadows: { xxl } }) => xxl,
        }}
      >
        <Box
          sx={{
            width: "100%",
            p: "30px",
          }}
        >
          {/* BREADCRUMB */}
          <Breadcrumbs
            separator="›"
            sx={{
              mb: 4,
              "& .MuiBreadcrumbs-ol": {
                alignItems: "center",
              },
            }}
          >
            <Link
              underline="hover"
              color="inherit"
              href="/"
              style={{
                fontWeight: 500,
              }}
            >
              Home
            </Link>
            <MKTypography variant="button" color="text" fontWeight="medium">
              Books
            </MKTypography>
          </Breadcrumbs>
          {/* MAIN LAYOUT */}
          <Box
            sx={{
              display: "grid",
              gridTemplateColumns: {
                xs: "1fr",
                lg: "280px 1fr",
              },
              gap: 1,
              alignItems: "start",
            }}
          >
            {/* SIDEBAR */}
            <Box
              sx={{
                bgcolor: "white",
                borderRadius: "24px",
                p: 2,
                border: "1px solid #ececec",
                position: "sticky",
                top: 100,
                height: "fit-content",
              }}
            >
              <MKTypography variant="h5" fontWeight="bold" mb={3}>
                Filters
              </MKTypography>

              {/* CATEGORY */}
              <MKTypography variant="button" fontWeight="bold" color="dark">
                Categories
              </MKTypography>

              <Box mt={2} display="flex" flexDirection="column" gap={1.2}>
                {["All", "Fiction", "Self-Help", "Fantasy", "Memoir", "Sci-Fi", "Historical"].map(
                  (g) => (
                    <Chip
                      key={g}
                      label={g}
                      clickable
                      onClick={() => setFilterGenre(g)}
                      color={filterGenre === g ? "primary" : "default"}
                      sx={{
                        justifyContent: "flex-start",
                        borderRadius: "12px",
                        height: 42,
                        fontWeight: 500,
                        transition: "0.2s",
                        "&:hover": {
                          transform: "translateX(4px)",
                        },
                      }}
                    />
                  )
                )}
              </Box>

              <Divider sx={{ my: 4 }} />

              {/* PRICE */}
              <MKTypography variant="button" fontWeight="bold" color="dark">
                Price Range
              </MKTypography>

              <Box px={1} mt={3}>
                <Slider
                  value={priceRange}
                  onChange={(_, value) => setPriceRange(value)}
                  valueLabelDisplay="auto"
                  min={0}
                  max={100}
                />

                <Box display="flex" justifyContent="space-between" mt={1}>
                  <MKTypography variant="caption">${priceRange[0]}</MKTypography>
                  <MKTypography variant="caption">${priceRange[1]}</MKTypography>
                </Box>

                <Box mt={2} display="flex" flexDirection="column" gap={1.2}>
                  {suppliers.map((s) => (
                    <Chip
                      key={s}
                      label={s}
                      clickable
                      onClick={() => setSupplierFilter(s)}
                      color={supplierFilter === s ? "primary" : "default"}
                      sx={{
                        justifyContent: "flex-start",
                        borderRadius: "12px",
                        height: 42,
                        fontWeight: 500,
                        transition: "0.2s",
                        "&:hover": {
                          transform: "translateX(4px)",
                        },
                      }}
                    />
                  ))}
                </Box>
              </Box>

              <Divider sx={{ my: 4 }} />
            </Box>

            {/* PRODUCT AREA */}
            <Box sx={{ pl: "40px" }}>
              {/* TOP BAR */}
              <Box
                sx={{
                  display: "flex",
                  justifyContent: "space-between",
                  alignItems: "center",
                  flexWrap: "wrap",
                  gap: 2,
                  mb: 4,
                  p: 2.5,
                  bgcolor: "white",
                  borderRadius: "24px",
                  border: "1px solid #ececec",
                }}
              >
                <TextField
                  size="small"
                  placeholder="Search books..."
                  value={search}
                  onChange={(e) => setSearch(e.target.value)}
                  sx={{
                    minWidth: {
                      xs: "100%",
                      md: 320,
                    },

                    "& .MuiOutlinedInput-root": {
                      borderRadius: "14px",
                    },
                  }}
                />

                <Box display="flex" alignItems="center" gap={2}>
                  <MKTypography variant="body2" color="text" fontWeight="medium">
                    {filteredBooks.length} books
                  </MKTypography>

                  <FormControl
                    size="small"
                    sx={{
                      minWidth: 180,
                    }}
                  >
                    <InputLabel>Sort</InputLabel>

                    <Select
                      value={sortBy}
                      onChange={(e) => setSortBy(e.target.value)}
                      label="Sort"
                      sx={{
                        borderRadius: "14px",
                      }}
                    >
                      <MenuItem value="featured">Featured</MenuItem>
                      <MenuItem value="price-asc">Price ↑</MenuItem>
                      <MenuItem value="price-desc">Price ↓</MenuItem>
                      <MenuItem value="title-asc">A-Z</MenuItem>
                      <MenuItem value="rating">Rating</MenuItem>
                    </Select>
                  </FormControl>
                </Box>
              </Box>

              {/* PRODUCT GRID */}
              <Box
                display="grid"
                gridTemplateColumns={{
                  xs: "repeat(1, 1fr)",
                  sm: "repeat(2, 1fr)",
                  md: "repeat(3, 1fr)",
                  lg: "repeat(4, 1fr)",
                  xl: "repeat(5, 1fr)",
                }}
                gap={2.5}
              >
                {paginatedBooks.map((book) => (
                  <BookCard
                    key={book.id}
                    id={book.id}
                    title={book.title}
                    author={book.author}
                    price={book.price}
                    originalPrice={book.originalPrice}
                    coverImage={book.coverImage}
                    badge={book.badge}
                    rating={book.rating}
                    onAddToCart={() => console.log("add", book.id)}
                    onWishlist={() => console.log("wishlist", book.id)}
                  />
                ))}
              </Box>
              <Box display="flex" justifyContent="center" mt={4}>
                <Pagination
                  count={Math.ceil(filteredBooks.length / itemsPerPage)}
                  page={page}
                  onChange={(e, value) => setPage(value)}
                  color="primary"
                />
              </Box>
            </Box>
          </Box>
        </Box>
      </Card>
      <MKBox pt={6} px={0} mt={6}>
        <DefaultFooter content={footerRoutes} />
      </MKBox>
    </>
  );
}
