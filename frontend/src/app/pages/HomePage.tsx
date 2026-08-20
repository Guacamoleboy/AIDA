// Pathing
// _______
// src/app/pages/HomePage.tsx

import SearchBar from "@/features/home-page/search-bar/SearchBar"
import BlogShowcase from "@/features/home-page/blog-showcase/BlogShowcase"

const HomePage = () => (
    <div className="homePage">

        {/* TOP ROW */}
        <SearchBar />

        {/* BOTTOM ROW */}
        <BlogShowcase />

    </div>
)

export default HomePage