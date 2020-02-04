package com.iznan.bookapps.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.iznan.bookapps.model.BookModel
import com.iznan.bookapps.repo.RemoteRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val listOfBook = mutableListOf("Remember You", "Eden", "The Ancient Story: Elements")
    private val listOfModelBook = mutableListOf(
        BookModel(
            12,
            "covers%2Fthe-ancient-story.jpg?download=false",
            "The Ancient Story: Elements"
        ),
        BookModel(
            20,
            "covers%2Fmonochrome.jpg?download=false",
            "MONOCHROME: MALLEUS MALEFICARUM"
        ),
        BookModel(
            29,
            "covers%2FSiera-dark-soul.jpg?download=false",
            "SIERA: Dark Soul"
        )
    )

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = DetailViewModel(RemoteRepository())
        createDummyData()
    }

    @Test
    fun getTitle() {
        Assert.assertNotNull(viewModel.title)
        Assert.assertEquals("TIME AUSSICHT", viewModel.title.value)
    }

    @Test
    fun getDesc() {
        Assert.assertNotNull(viewModel.desc)
        Assert.assertEquals(
            "Dia menyadari hal ini pada umur 12 tahun, tapi dia tetap saja tidak mengerti apa yang disebut dengan ‘Aliran Waktu' " +
                    "sampai setahun kemudian. Aliran Waktu tidak bisa dideskripsikan dengan kata-kata, tapi juga tidak bisa digambarkan " +
                    "dengan jelas melalui sebuah gambar. Dia pernah mencoba untuk membuat gambaran tentang Aliran Waktu melalui sebuah gambar. " +
                    "Tapi orang-orang di sekelilingnya tidak mengerti apa yang ia gambar dan mengira kalau itu adalah frekuensi bunyi. " +
                    "Aliran Waktu itulah yang akan mengubah seluruh kehidupan Haru dan membuatnya bertemu dengan orang-orang yang akan berjalan bersamanya..",
            viewModel.desc.value
        )
    }

    @Test
    fun getWriter() {
        Assert.assertNotNull(viewModel.writer)
        Assert.assertEquals("Emit Nightrya", viewModel.writer.value)
    }

    @Test
    fun getPoster() {
        Assert.assertNotNull(viewModel.poster)
        Assert.assertEquals(
            "https://cabaca.id/share_book.php?title=time-aussicht&book=21",
            viewModel.poster.value
        )
    }

    @Test
    fun getGenres() {
        Assert.assertNotNull(viewModel.poster)
        Assert.assertEquals(
            "https://cabaca.id/share_book.php?title=time-aussicht&book=21",
            viewModel.poster.value
        )
    }

    @Test
    fun getStatus() {
        Assert.assertNotNull(viewModel.status)
        Assert.assertEquals("bersambung", viewModel.status.value)
    }

    @Test
    fun getEmail() {
        Assert.assertNotNull(viewModel.email)
        Assert.assertEquals("fatimah.azzah@gmail.com", viewModel.email.value)
    }

    @Test
    fun getFollower() {
        Assert.assertNotNull(viewModel.follower)
        Assert.assertEquals("120", viewModel.follower.value)
    }

    @Test
    fun getKarya() {
        val int = (0..2).random()
        Assert.assertNotNull(viewModel.karya)
        Assert.assertEquals(listOfBook[int], viewModel.karya.value?.get(int))
    }

    @Test
    fun getRelatedBooks() {
        val int = (0..2).random()
        Assert.assertNotNull(viewModel.relatedBooks)
        Assert.assertEquals(
            listOfModelBook[int].title,
            viewModel.relatedBooks.value?.get(int)?.title
        )
        Assert.assertEquals(listOfModelBook[int].id, viewModel.relatedBooks.value?.get(int)?.id)
        Assert.assertEquals(
            listOfModelBook[int].image,
            viewModel.relatedBooks.value?.get(int)?.image
        )
    }

    fun createDummyData() {
        viewModel.title.value = "TIME AUSSICHT"
        viewModel.desc.value =
            "Dia menyadari hal ini pada umur 12 tahun, tapi dia tetap saja tidak mengerti apa yang disebut dengan ‘Aliran Waktu' " +
                    "sampai setahun kemudian. Aliran Waktu tidak bisa dideskripsikan dengan kata-kata, tapi juga tidak bisa digambarkan " +
                    "dengan jelas melalui sebuah gambar. Dia pernah mencoba untuk membuat gambaran tentang Aliran Waktu melalui sebuah gambar. " +
                    "Tapi orang-orang di sekelilingnya tidak mengerti apa yang ia gambar dan mengira kalau itu adalah frekuensi bunyi. " +
                    "Aliran Waktu itulah yang akan mengubah seluruh kehidupan Haru dan membuatnya bertemu dengan orang-orang yang akan berjalan bersamanya.."
        viewModel.writer.value = "Emit Nightrya"
        viewModel.poster.value = "https://cabaca.id/share_book.php?title=time-aussicht&book=21"
        viewModel.genres.value = mutableListOf("Fantasy", "Romance", "Adult", "Comedy")
        viewModel.status.value = "bersambung"
        viewModel.email.value = "fatimah.azzah@gmail.com"
        viewModel.follower.value = "120"
        viewModel.karya.value = listOfBook
        viewModel.relatedBooks.value = listOfModelBook
    }
}