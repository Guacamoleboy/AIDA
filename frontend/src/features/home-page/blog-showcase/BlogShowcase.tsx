// Pathing
// _______
// src/features/home-page/blog-showcase/BlogShowcase.tsx

import BlogEntries from './BlogEntries'
import styles from './BlogShowcase.module.css'

const BlogShowcase = () => {
    return (
        <section className={styles.blogShowcase}>

            <div className={styles.blogEntries}>

                {/* CHILD */}
                <BlogEntries />

            </div>

        </section>
    )
}

export default BlogShowcase