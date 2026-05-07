import { useState, useMemo } from "react";
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

import BookCard from "../../../components/MKBookCard";
import { BookData } from "data/mockBooks";

export default function ProductPage() {
  const [sortBy, setSortBy] = useState("featured");
  const [filterGenre, setFilterGenre] = useState("All");
  const [viewMode, setViewMode] = useState("grid");

  const filteredBooks = useMemo(() => {
    let result = [...BookData];

    // filter
    if (filterGenre !== "All") {
      result = result.filter((b) => b.genre === filterGenre);
    }

    // sort
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
    <Container sx={{ py: 4 }}>
      {/* FILTER + SORT */}
      <Box display="flex" justifyContent="space-between" mb={3}>
        <Box display="flex" gap={1} flexWrap="wrap">
          {["All", "Fiction", "Self-Help", "Fantasy", "Memoir", "Sci-Fi", "Historical"].map((g) => (
            <Chip
              key={g}
              label={g}
              clickable
              onClick={() => setFilterGenre(g)}
              color={filterGenre === g ? "primary" : "default"}
            />
          ))}
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

          <ToggleButtonGroup value={viewMode} exclusive onChange={(_, v) => v && setViewMode(v)}>
            <ToggleButton value="grid">
              <ViewModule />
            </ToggleButton>
            <ToggleButton value="list">
              <ViewList />
            </ToggleButton>
          </ToggleButtonGroup>
        </Box>
      </Box>

      {/* GRID / LIST */}
      <Box
        display="grid"
        gridTemplateColumns={viewMode === "grid" ? "repeat(auto-fill, minmax(220px, 1fr))" : "1fr"}
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
  );
}
