// Pathing
// _______
// src/app/routes/AppRoutes.tsx

import { Routes, Route } from 'react-router-dom'

import HomePage from '@/app/pages/HomePage'
import BlogPage from '@/app/pages/BlogPage'

import AppLayout from '@/app/layout/AppLayout'
import BlogLayout from '@/app/layout/BlogLayout'

const AppRoutes = () => (
    <Routes>

        {/* APP */}
        <Route element={<AppLayout />}>
            <Route path="/" element={<HomePage />} />
        </Route>

        {/* BLOG */}
        <Route element={<BlogLayout />}>
            <Route path="/blog/:id" element={<BlogPage />} />
        </Route>

    </Routes>
)

export default AppRoutes