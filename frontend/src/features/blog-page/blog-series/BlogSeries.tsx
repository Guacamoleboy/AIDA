// Pathing
// _______
// src/features/blog-page/blog-series/BlogSeries.tsx

import { Link, useParams } from 'react-router-dom'
import styles from './BlogSeries.module.css'
import meta from '@/shared/data/blog/meta.json'

// ------------------------------------------------------------------------------------------------------

const BlogSeries = () => {

    // Locate which url we're on
    const { id } = useParams<{ id: string }>()

    // Gets the ID from the URL
    const currentId = Number(id)

    // Find the current blog
    const currentBlog = meta.find(
        (blog) => blog.id === currentId,
    )

    // Check if it's part of a series
    if (!currentBlog?.series) {
        return null
    }

    // Locate all blogs for the same series
    const seriesBlogs = meta.filter(
        (blog) => blog.series === currentBlog.series,
    )

    // If only 1 blog is found.. Don't show it's part of a series.
    if (seriesBlogs.length <= 1) {
        return null
    }

    return (
        <details className={styles.blogSeries}>

            <summary className={styles.blogSeriesSummary}>
                <span>
                    This blog is part of a series
                </span>
                <i className={`fa fa-plus ${styles.blogSeriesIcon}`} />
                <i className={`fa fa-minus ${styles.blogSeriesIconOpen}`} />
            </summary>

            <div className={styles.blogSeriesContent}>

                <ol className={styles.blogSeriesList}>

                    {seriesBlogs.map((blog) => (

                        <li
                            key={blog.id}
                            className={
                                blog.id === currentId
                                    ? styles.blogSeriesCurrent
                                    : undefined
                            }
                        >

                            {blog.id === currentId ? (

                                <span>
                                    {blog.title}
                                </span>

                            ) : (

                                <Link to={`/blog/${blog.id}`}>
                                    {blog.title}
                                </Link>

                            )}

                        </li>

                    ))}

                </ol>

            </div>

        </details>
    )
}

export default BlogSeries