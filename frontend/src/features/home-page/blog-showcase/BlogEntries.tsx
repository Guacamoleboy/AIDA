// Pathing
// _______
// src/features/home-page/blog-showcase/BlogEntries.tsx

import data from '@/shared/data/blog/meta.json'
import BlogEntry from './BlogEntry'

const BlogEntries = () => {
    return (
        <>
            {data.map((entry) => (
                <BlogEntry
                    key={entry.id}
                    id={entry.id}
                    title={entry.title}
                    description={entry.description}
                    date={entry.date}
                />
            ))}
        </>
    )
}

export default BlogEntries