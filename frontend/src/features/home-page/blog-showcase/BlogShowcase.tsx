// Pathing
// _______
// src/features/home-page/blog-showcase/BlogShowcase.tsx

import BlogEntries from './BlogEntries'
import styles from './BlogShowcase.module.css'

const BlogShowcase = () => {
    return (
        <section className={styles.blogShowcase}>

            <BlogEntries />

        </section>
    )
}

export default BlogShowcase