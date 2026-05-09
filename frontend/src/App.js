import { useEffect } from "react";
import { Routes, Route, Navigate, useLocation } from "react-router-dom";

import { ThemeProvider } from "@mui/material/styles";
import CssBaseline from "@mui/material/CssBaseline";

import theme from "assets/theme";
import routes from "routes";

export default function App() {
  const { pathname } = useLocation();

  useEffect(() => {
    document.documentElement.scrollTop = 0;
    document.scrollingElement.scrollTop = 0;
  }, [pathname]);

  const getRoutes = (allRoutes) =>
    allRoutes.flatMap((route, index) => {
      if (route.collapse) {
        return getRoutes(route.collapse);
      }

      if (!route.route) return null;

      return <Route key={route.route + index} path={route.route} element={route.component} />;
    });
  console.log(getRoutes(routes));
  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <Routes>
        {getRoutes(routes)}

        {/* fallback */}
        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
    </ThemeProvider>
  );
}
