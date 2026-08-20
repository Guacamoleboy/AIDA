// Pathing
// _______
// src/app/layout/BlogLayout.tsx

import { Outlet } from 'react-router-dom'
import Return from '@/shared/components/return/Return'

function BlogLayout() {
    return (
        <div className="blogLayout">

            {/* RETURN */}
            <Return />

            <Outlet />

        </div>
    )
}

export default BlogLayout