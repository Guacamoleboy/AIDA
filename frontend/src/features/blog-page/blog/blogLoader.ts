// Pathing
// _______
// src/features/blog-page/blog/blogLoader.ts

import blogs from './data/blogs'

// ------------------------------------------------------------------------------------------------------

export const getBlogContent = (
    id: string,
): string | undefined => {

    const blogPath = Object.keys(blogs).find(
        (path) => path.endsWith(`/${id}.md`),
    )

    return blogPath
        ? blogs[blogPath]
        : undefined
}