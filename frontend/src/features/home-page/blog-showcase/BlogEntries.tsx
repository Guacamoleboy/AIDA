// Pathing
// _______
// src/features/home-page/blog-showcase/BlogEntries.tsx

import data from '@/shared/data/blog/meta.json'
import BlogEntry from './BlogEntry'
import styles from './BlogShowcase.module.css'

const BlogEntries = () => {

    const blogEntryPages = data.reduce<(typeof data)[]>((pages, entry, index) => {
        const pageIndex = Math.floor(index / 2)

        if (!pages[pageIndex]) {
            pages[pageIndex] = []
        }

        pages[pageIndex].push(entry)

        return pages
    }, [])

    return (
        <>
            {blogEntryPages.map((entries, index) => (
                <div
                    key={index}
                    className={styles.blogEntryPage}
                >
                    {entries.map((entry) => (
                        <BlogEntry
                            key={entry.id}
                            id={entry.id}
                            title={entry.title}
                            description={entry.description}
                            date={entry.date}
                        />
                    ))}
                </div>
            ))}
        </>
    )
}

export default BlogEntries