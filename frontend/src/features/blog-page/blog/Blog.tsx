// Pathing
// _______
// src/features/blog-page/blog/Blog.tsx

import { useParams } from 'react-router-dom'
import ReactMarkdown from 'react-markdown'
import { getBlogContent } from './blogLoader'
import meta from '@/shared/data/blog/meta.json'
import styles from './Blog.module.css'
import BlogImage from './BlogImage'
import BlogSeries from '@/features/blog-page/blog-series/BlogSeries'

// ------------------------------------------------------------------------------------------------------

const Blog = () => {
    
    const { id } = useParams<{ id: string }>()

    const blog = meta.find(
        (entry) => entry.id === Number(id),
    )

    const blogContent = id
        ? getBlogContent(id)
        : undefined

    if (!blog || !blogContent) {
        return (
            <article className={styles.blogWrapper}>

                <div className={styles.blogMeta}>
                    <h1>Blog not found</h1>
                </div>

            </article>
        )
    }

    return (
        <article className={styles.blogWrapper}>

            {/* META DATA */}
            <header className={styles.blogMeta}>

                <p className={styles.blogDate}>
                    {blog.date}
                </p>

                <h1 className={styles.blogTitle}>
                    {blog.title}
                </h1>

                <p className={styles.blogDescription}>
                    {blog.description}
                </p>

            </header>

            {/* Blog Series*/}
            <BlogSeries />

            {/* BLOG CONTENT */}
            <section className={styles.blogContent}>

                <ReactMarkdown

                    // Image Styling
                    components={{
                        img: BlogImage,
                    }}

                >
                    {/* Remaining Content */}
                    {blogContent}

                </ReactMarkdown>

            </section>

        </article>
    )
}

export default Blog