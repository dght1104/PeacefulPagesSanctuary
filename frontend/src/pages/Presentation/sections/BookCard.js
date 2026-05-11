import { useState, useMemo } from "react";
import { Card, Box, Typography, Button, IconButton, Chip } from "@mui/material";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
import FavoriteIcon from "@mui/icons-material/Favorite";
import ShoppingCartOutlinedIcon from "@mui/icons-material/ShoppingCartOutlined";
import { Link as RouterLink } from "react-router-dom";
import { BookData } from "data/mockBooks";

/* eslint-disable react/prop-types */
export default function BookCard({ id, onAddToCart, onWishlist }) {
  const book = useMemo(() => {
    return BookData.find((b) => String(b.id) === String(id));
  }, [id]);

  const [wishlisted, setWishlisted] = useState(false);
  const [added, setAdded] = useState(false);

  if (!book) return null;

  const handleWishlist = () => {
    setWishlisted((prev) => !prev);
    onWishlist?.(book);
  };

  const handleAddToCart = () => {
    setAdded(true);
    onAddToCart?.(book);

    // Sau 3 giây đổi lại thành "Add to Cart"
    setTimeout(() => {
      setAdded(false);
    }, 3000);
  };

  return (
    <Card
      elevation={0}
      sx={{
        width: 220,
        height: 320,
        borderRadius: "16px",
        border: "1px solid",
        borderColor: "grey.100",
        display: "flex",
        flexDirection: "column",
        overflow: "hidden",
        transition: "0.25s",
        "&:hover": {
          transform: "translateY(-6px)",
          boxShadow: "0 16px 36px rgba(0,0,0,0.13)",
        },
      }}
    >
      {/* IMAGE */}
      <Box sx={{ height: "60%", position: "relative", overflow: "hidden" }}>
        <Box
          component="img"
          src={book.coverImage}
          alt={book.title}
          sx={{
            width: "100%",
            height: "100%",
            objectFit: "cover",
            transition: "0.3s",
            "&:hover": { transform: "scale(1.05)" },
          }}
        />

        {book.badge && (
          <Chip
            label={book.badge}
            size="small"
            sx={{
              position: "absolute",
              top: 8,
              left: 8,
              bgcolor: "error.main",
              color: "#fff",
              fontWeight: 700,
              fontSize: "0.7rem",
            }}
          />
        )}

        <IconButton
          size="small"
          onClick={handleWishlist}
          sx={{
            position: "absolute",
            top: 6,
            right: 6,
            bgcolor: "rgba(255,255,255,0.9)",
          }}
        >
          {wishlisted ? (
            <FavoriteIcon sx={{ fontSize: 16, color: "error.main" }} />
          ) : (
            <FavoriteBorderIcon sx={{ fontSize: 16 }} />
          )}
        </IconButton>
      </Box>

      {/* CONTENT */}
      <Box
        sx={{
          height: "40%",
          display: "flex",
          flexDirection: "column",
          justifyContent: "space-between",
          p: 1.5,
        }}
      >
        <Box>
          <Typography
            component={RouterLink}
            to={`/product/${book.id}`}
            sx={{
              fontSize: "0.8rem",
              fontWeight: 600,
              textDecoration: "none",
              color: "text.primary",
              display: "-webkit-box",
              WebkitLineClamp: 2,
              WebkitBoxOrient: "vertical",
              overflow: "hidden",
              "&:hover": { color: "primary.main" },
            }}
          >
            {book.title}
          </Typography>

          <Typography sx={{ fontSize: "0.7rem", color: "text.disabled" }}>{book.author}</Typography>
        </Box>

        {/* PRICE */}
        <Box sx={{ display: "flex", gap: 1 }}>
          <Typography sx={{ fontWeight: 700, color: "primary.main" }}>
            ${book.price.toFixed(2)}
          </Typography>

          {book.originalPrice > book.price && (
            <Typography
              sx={{
                textDecoration: "line-through",
                fontSize: "0.75rem",
                color: "text.disabled",
              }}
            >
              ${book.originalPrice.toFixed(2)}
            </Typography>
          )}
        </Box>

        {/* BUTTON */}

        <Button
          fullWidth
          size="small"
          variant="contained"
          disableElevation
          onClick={handleAddToCart}
          startIcon={<ShoppingCartOutlinedIcon />}
          sx={{
            borderRadius: "10px",
            fontSize: "0.75rem",
            textTransform: "none",
            bgcolor: "#A3C1FF !important",
            color: "#1E3A5F",
            fontWeight: 600,
            boxShadow: "none !important",

            // Giữ nguyên màu ở mọi trạng thái
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
        </Button>
      </Box>
    </Card>
  );
}
