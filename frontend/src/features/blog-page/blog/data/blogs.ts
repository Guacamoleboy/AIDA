// Pathing
// _______
// src/features/blog-page/blog/data/blogs.ts

const blogs = import.meta.glob(
    '@/shared/data/blog/*.md',
    {
        eager: true,
        query: '?raw',
        import: 'default',
    },
) as Record<string, string>

export default blogs