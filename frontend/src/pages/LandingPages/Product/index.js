import { useState, useMemo } from "react";

// MUI
import {
  Container,
  Box,
  Chip,
  ToggleButtonGroup,
  ToggleButton,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
} from "@mui/material";

import { ViewModule, ViewList } from "@mui/icons-material";

// Layout
import DefaultNavbar from "examples/Navbars/DefaultNavbar";
import DefaultFooter from "examples/Footers/DefaultFooter";
import MKBox from "components/MKBox";
import MKTypography from "components/MKTypography";

// Data + Component
import BookCard from "components/MKBookCard";
import { BookData } from "data/mockBooks";

// Routes
import routes from "routes";
import footerRoutes from "footer.routes";

import bgImage from "assets/images/bg-about-us.jpg";

export default function ProductPage() {
  const [sortBy, setSortBy] = useState("featured");
  const [filterGenre, setFilterGenre] = useState("All");
  const [viewMode, setViewMode] = useState("grid");

  const filteredBooks = useMemo(() => {
    let result = [...BookData];

    if (filterGenre !== "All") {
      result = result.filter((b) => b.genre === filterGenre);
    }

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
  }, [sortBy, filterGenre]);

  return (
    <>
      {/* NAVBAR */}
      <DefaultNavbar routes={routes} transparent light />

      {/* HERO */}
      <MKBox
        minHeight="40vh"
        sx={{
          backgroundImage: `linear-gradient(rgba(0,0,0,0.6), rgba(0,0,0,0.6)), url(${bgImage})`,
          backgroundSize: "cover",
          backgroundPosition: "center",
          display: "grid",
          placeItems: "center",
          position: "relative",
          zIndex: 1,
        }}
      >
        <Container sx={{ textAlign: "center" }}>
          <MKTypography variant="h2" color="white" fontWeight="bold">
            PRODUCT
          </MKTypography>

          <MKTypography variant="body1" color="white" opacity={0.8} mt={1}>
            Discover your next favorite book
          </MKTypography>
        </Container>
      </MKBox>

      {/* CONTENT CARD */}
      <MKBox
        component="section"
        sx={{
          mx: { xs: 2, lg: 3 },
          mt: 0,
          mb: 4,
          p: 3,
          borderRadius: 3,
          bgcolor: "white",
          boxShadow: ({ boxShadows: { xxl } }) => xxl,
        }}
      >
        <Container>
          {/* FILTER + SORT */}
          <Box
            sx={{
              display: "flex",
              justifyContent: "space-between",
              mb: 3,
              flexWrap: "wrap",
              gap: 2,
              bgcolor: "white",
              p: 2,
              borderRadius: 2,
              boxShadow: "0 4px 20px rgba(229, 221, 221, 0.08)",
            }}
          >
            {/* FILTER CHIPS */}
            <Box display="flex" gap={1} flexWrap="wrap">
              {["All", "Fiction", "Self-Help", "Fantasy", "Memoir", "Sci-Fi", "Historical"].map(
                (g) => (
                  <Chip
                    key={g}
                    label={g}
                    clickable
                    onClick={() => setFilterGenre(g)}
                    color={filterGenre === g ? "primary" : "default"}
                  />
                )
              )}
            </Box>

            <Box display="flex" gap={2}>
              <FormControl size="small">
                <InputLabel>Sort</InputLabel>
                <Select value={sortBy} onChange={(e) => setSortBy(e.target.value)} label="Sort">
                  <MenuItem value="featured">Featured</MenuItem>
                  <MenuItem value="price-asc">Price ↑</MenuItem>
                  <MenuItem value="price-desc">Price ↓</MenuItem>
                  <MenuItem value="title-asc">A-Z</MenuItem>
                  <MenuItem value="rating">Rating</MenuItem>
                </Select>
              </FormControl>

              <ToggleButtonGroup
                value={viewMode}
                exclusive
                onChange={(_, v) => v && setViewMode(v)}
              >
                <ToggleButton value="grid">
                  <ViewModule />
                </ToggleButton>
                <ToggleButton value="list">
                  <ViewList />
                </ToggleButton>
              </ToggleButtonGroup>
            </Box>
          </Box>

          {/* GRID */}
          <Box
            display="grid"
            gridTemplateColumns={
              viewMode === "grid" ? "repeat(auto-fill, minmax(220px, 1fr))" : "1fr"
            }
            gap={2}
          >
            {filteredBooks.map((book) => (
              <BookCard
                key={book.id}
                id={book.id}
                title={book.title}
                author={book.author}
                price={book.price}
                originalPrice={book.originalPrice}
                coverImage={book.coverImage}
                badge={book.badge}
                onAddToCart={() => console.log("add", book.id)}
                onWishlist={() => console.log("wishlist", book.id)}
              />
            ))}
          </Box>
        </Container>
      </MKBox>

      {/* FOOTER */}
      <MKBox pt={6}>
        <DefaultFooter content={footerRoutes} />
      </MKBox>
    </>
  );
}
